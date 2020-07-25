package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.model.User;


public interface UserService {
    
    void insertUser(User user);
  
    boolean getUserByUserName(String userName);
    
    User getUserDetailByUserName(String userName);

    String generateSalt(int n);
}

