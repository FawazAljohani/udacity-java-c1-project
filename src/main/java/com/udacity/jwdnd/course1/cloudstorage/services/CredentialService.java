package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;


    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public int createCredential(Credential credential){

        String password = credential.getPassword();
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];

        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(password, encodedKey);

        credential.setKey(encodedKey);
        credential.setPassword(encryptedPassword);

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
