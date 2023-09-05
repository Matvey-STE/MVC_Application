package com.matveyvs.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private String name;
    private String age;
    private String email;
    private String login;
    private String password;

    public User(@JsonProperty("name") String name, @JsonProperty("age") String age,
                @JsonProperty("email") String email, @JsonProperty("login") String login,
                @JsonProperty("password") String password) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
               "name='" + name + '\'' +
               ", age='" + age + '\'' +
               ", email='" + email + '\'' +
               ", login='" + login + '\'' +
               ", password='" + password + '\'' +
               '}';
    }
}
