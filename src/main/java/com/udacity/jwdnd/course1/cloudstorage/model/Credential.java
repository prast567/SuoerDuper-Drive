package com.udacity.jwdnd.course1.cloudstorage.model;

import java.io.Serializable;

public class Credential implements Serializable {

    private Integer id;
    private String url;
    private String userName;
    private String key;
    private String password;
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        //EncryptionService encript = new EncryptionService();
        //String encripted = encript.encryptValue(password,"5654332214345443");
        //this.password = encripted;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Credential{" + "id=" + id + ", credentialUrl=" + url + ", credentialusername=" + userName + ", password=" + password + '}';
    }


}
