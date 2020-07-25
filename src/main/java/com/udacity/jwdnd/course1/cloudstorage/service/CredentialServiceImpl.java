
package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("credentialService")
public class CredentialServiceImpl implements CredentialService {

    @Autowired
    private CredentialMapper credentialMapper;

    @Autowired
    private EncryptionService encryptionService;

    @Override
    public List<Credential> findCredentialsByUserId(Integer userId) {
        return credentialMapper.findCredentialsByUserId(userId);
    }

    public void updateCredential(Credential credential) {
        credentialMapper.updateCredentials(encryptPassword(credential));
    }
    
    @Transactional
    @Override
    public void insertCredential(Credential addCredential) {
        credentialMapper.insertCredential(encryptPassword(addCredential));
    }

    private Credential encryptPassword(Credential credential) {
        String key = RandomStringUtils.random(16, true, true);
        credential.setKey(key);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), key));
        return credential;
    }

    private Credential decryptPassword(Credential credential) {
        credential.setPassword(encryptionService.decryptValue(credential.getPassword(), credential.getKey()));
        return credential;
    }

    @Override
    public boolean getCredentialByuserName(String userName) {
        Credential addCredential = credentialMapper.getCredentialByuserName(userName);

        if(addCredential != null) {
            return true;
        }

        return false;
    }

    @Override
    public List<Credential> findAllCredentials(){
        return credentialMapper.findAllCredentials();
    }

    @Override
    public void deleteCredential(String userName) {
        credentialMapper.deleteCredential(userName);
    }
}