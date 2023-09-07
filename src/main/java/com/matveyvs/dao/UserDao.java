package com.matveyvs.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.matveyvs.entity.UserEntity;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Serializable {
    private final String path;

    public UserDao(String filePath) {
        this.path = filePath;
    }

    public Optional<List<UserEntity>> getAllUsersFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<UserEntity> listOfUsers;

        File jsonFile = new File(path);
        if (!jsonFile.exists() || jsonFile.length() == 0) {
            return Optional.empty();
        }

        try {
            listOfUsers = objectMapper.readValue(jsonFile, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.ofNullable(listOfUsers);
    }

    public boolean writeUserToJson(UserEntity user) {
        List<UserEntity> listOfUser = getAllUsersFromJson().orElse(new ArrayList<>());
        listOfUser.add(user);
        return writeListToJson(listOfUser);
    }

    private boolean writeListToJson(List<UserEntity> listOfUser) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            objectMapper.writeValue(new File(path), listOfUser);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeUserByLogin(String login) {
        Optional<List<UserEntity>> allUsersFromJson = getAllUsersFromJson();
        if (allUsersFromJson.isPresent()) {
            List<UserEntity> users = allUsersFromJson.get();
            Optional<UserEntity> userByLogin = getUserByLogin(login);
            if (userByLogin.isPresent()) {
                UserEntity user = userByLogin.get();
                users.remove(user);
                return writeListToJson(users);
            }
        }
        return false;
    }

    public Optional<UserEntity> getUserByLogin(String login) {
        Optional<List<UserEntity>> listOfUser = getAllUsersFromJson();

        return listOfUser.flatMap(users -> users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst());
    }

    public boolean updateUserByLogin(String login, UserEntity user) {
        Optional<List<UserEntity>> allUsersFromJson = getAllUsersFromJson();
        if (allUsersFromJson.isPresent()) {
            List<UserEntity> users = allUsersFromJson.get();
            users.removeIf(us -> us.getLogin().equals(login));
            users.add(user);
            return writeListToJson(users);
        }
        return false;
    }


    public boolean isLoginExists(String login) {
        return getUserByLogin(login).isPresent();
    }

    public boolean isEmailExists(String email) {
        Optional<List<UserEntity>> listOfUser = getAllUsersFromJson();
        return listOfUser.map(users -> users.stream().anyMatch(user -> user.getEmail().equals(email)))
                .orElse(false);
    }

    public Optional<String> getUserPasswordByLogin(String login) {
        return getUserByLogin(login)
                .map(UserEntity::getPassword);
    }
}
