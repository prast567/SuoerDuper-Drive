package com.udacity.jwdnd.course1.cloudstorage.model;

import java.io.Serializable;

public class UserLogin implements Serializable {

    private String userName;

    private String password;

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
