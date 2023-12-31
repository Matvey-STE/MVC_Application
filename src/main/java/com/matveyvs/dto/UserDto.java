package com.matveyvs.dto;

import com.matveyvs.dao.UserDao;
import com.matveyvs.entity.UserEntity;

import java.io.Serializable;
import java.util.Optional;

public class UserDto implements Serializable {
    UserDao userDao = new UserDao("/Users/matvey/MyProjects/MVC_Application/src/main/resources/file.json");

    public boolean addUser(UserEntity user) {
        return userDao.writeUserToJson(user);
    }

    public Optional<UserEntity> getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    public Optional<String> getPasswordByLogin(String login) {
        return userDao.getUserPasswordByLogin(login);
    }

    public boolean isLoginExists(String login) {
        return userDao.isLoginExists(login);
    }

    public boolean isEmailExists(String email) {
        return userDao.isEmailExists(email);
    }

    public boolean updateUserByLogin(String login, UserEntity user) {
        return userDao.updateUserByLogin(login, user);
    }

}
