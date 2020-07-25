package com.udacity.jwdnd.course1.cloudstorage.model;

import java.io.Serializable;

public class User implements Serializable {

    private Integer userId;
    
    private String userName;

    private String salt;
    
    private String firstName;

    private String lastName;

    private String password;
    
    

    public Integer getUserId() {
        return userId;
    }
    
    public String getSalt() {
        return salt;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password + ", salt=" + salt + '}';
    }

    
}
