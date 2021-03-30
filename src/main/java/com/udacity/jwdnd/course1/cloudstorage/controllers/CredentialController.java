package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    private final CredentialService credentialService;
    private final EncryptionService encryptionService;

    public CredentialController(CredentialService credentialService, EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
        this.credentialService = credentialService;
    }

    @PostMapping
    public String createCredential(@ModelAttribute("credentials") Credential credential, Model model){

        String returnMessage = null;

        if(credentialService.getCredential(credential.getCredentialId()) != null){
            model.addAttribute("successMessage", "Credential has been updated successfully");
            credentialService.updateCredential(credential);
        }

        int rowsAdded = credentialService.createCredential(credential);
        if(rowsAdded < 0){
            returnMessage = "Something wrong occurred while creating the credential, please try again";
        }

        if (returnMessage == null) {
            System.out.printf("created credential successfully");
            model.addAttribute("successMessage", true);
        } else {
            model.addAttribute("errorMessage", returnMessage);
        }

        return "redirect:home";
    }

    @GetMapping("/{credentialId}")
    public String viewCredential(@PathVariable("credentialId") Integer credentialId, Model model){

        Credential cred = credentialService.getCredential(credentialId);
        String decryptedPassword = encryptionService.decryptValue(cred.getPassword(), cred.getKey());
        Credential newCredential = new Credential(cred.getCredentialId(), cred.getUrl(), cred.getUserName(), cred.getKey(), decryptedPassword, cred.getUserId());



        return "redirect:home";
    }


}
