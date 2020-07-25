package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;


@Mapper
public interface UserMapper {

    @Insert("INSERT INTO users (USERNAME, PASSWORD, FIRSTNAME, LASTNAME, SALT) VALUES (#{userName}, #{password}, #{firstName}, #{lastName}, #{salt})")
    public void insertUser(User user);

    @Select("SELECT USERID as userId, USERNAME as userName, PASSWORD as password, "
            + "FIRSTNAME as firstName, LASTNAME as lastName, SALT as salt "
            + "FROM users WHERE userName = #{userName}")
    public User getUserByUserName(String userName);
    
    @Select("SELECT USERNAME as userName, PASSWORD as password, "
            + "FIRSTNAME as firstName, LASTNAME as lastName, "
            + "FROM users WHERE userName = #{userName}")
    public User getUserDetailByUserName(String userName);
    

}


