package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AuthService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;     

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        
        User user = userMapper.getUserByUserName(userName);

        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + userName);
        }
        
        return new UserPrincipal(user);
    }

    /**
     * Create grant authorities list for the user
     * @param role Role name
     * @return 
     */
    private static List<GrantedAuthority> getAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

}
