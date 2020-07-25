package com.udacity.jwdnd.course1.cloudstorage.config;

import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.UserPrincipal;
import com.udacity.jwdnd.course1.cloudstorage.service.HashService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private HashService hashService;
    
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String password = authentication.getCredentials().toString();
        String userName = authentication.getPrincipal().toString();
        User user = userMapper.getUserByUserName(userName);
        if(user != null) {
            password = hashService.getHashedValue(password, user.getSalt());
            if(password.equals(user.getPassword())) {
                UserPrincipal principal = new UserPrincipal(user);
                return new UsernamePasswordAuthenticationToken(principal    , password, new ArrayList<>());
            } else {
                return null;
            }
        } else {
            return null;
        }
        
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
