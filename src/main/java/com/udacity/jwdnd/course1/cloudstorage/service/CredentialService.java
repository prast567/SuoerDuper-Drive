package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;

import java.util.List;


public interface CredentialService {

    public List<Credential> findCredentialsByUserId(Integer userId);
    
    void insertCredential(Credential addCredential);
    
    boolean getCredentialByuserName(String userName);
    
    List<Credential> findAllCredentials();
    
    void deleteCredential(String userName);

    public void updateCredential(Credential credential);
}