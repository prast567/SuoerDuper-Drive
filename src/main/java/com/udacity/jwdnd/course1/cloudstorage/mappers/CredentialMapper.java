package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import java.util.List;

import org.apache.ibatis.annotations.*;


@Mapper
public interface CredentialMapper {
    
    @Select("SELECT ID AS id, URL as url, USERNAME as userName, KEY as key, PASSWORD as password, USERID as userId FROM credentials WHERE USERID=#{userId}")
    public List<Credential> findCredentialsByUserId(Integer userId);
    
    @Insert("INSERT INTO credentials (URL, USERNAME, PASSWORD, key, USERID) VALUES (#{url}, #{userName}, #{password}, #{key}, #{userId})")
    public void insertCredential(Credential addCredential);

    @Select("SELECT URL as url, USERNAME as userName, PASSWORD as password "
            + "FROM credentials WHERE userName = #{userName}")
    public Credential getCredentialByuserName(String userName);

    @Select("Select URL as url, USERNAME as userName, PASSWORD as password from credentials")
    public List<Credential> findAllCredentials();

    @Delete("DELETE FROM credentials where USERNAME= #{userName}")
    public void deleteCredential(String userName);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{userName}, key = #{key}, password = #{password} WHERE id = #{id}")
    public void updateCredentials(Credential credential);
}