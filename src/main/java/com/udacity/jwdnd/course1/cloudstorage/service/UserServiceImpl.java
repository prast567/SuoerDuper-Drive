package com.udacity.jwdnd.course1.cloudstorage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.HashService;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HashService hashService;

    @Transactional
    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public boolean getUserByUserName(String userName) {
        User user = userMapper.getUserByUserName(userName);

        if (user != null) {
            return true;
        }

        return false;
    }
    
    @Override
    public User getUserDetailByUserName(String userName) {
        User user = userMapper.getUserDetailByUserName(userName);

            return user;

    }

    public String generateSalt(int n) {

        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb 
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
