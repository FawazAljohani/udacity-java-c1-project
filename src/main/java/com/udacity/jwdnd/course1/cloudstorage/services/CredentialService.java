package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public int createCredential(Credential credential){

        return credentialMapper.createCredential(credential);
    }

    public Credential getCredential(Integer credentialId){
        return credentialMapper.getCredential(credentialId);
    }

    public List<Credential> getAllCredentials(Integer userId){
        return credentialMapper.getAllCredentials(userId);
    }

    public int updateCredential(Credential credential){
        return credentialMapper.updateCredential(credential);
    }

    public int deleteCredential(Integer credentialId){
        return credentialMapper.deleteCredential(credentialId);
    }

}
