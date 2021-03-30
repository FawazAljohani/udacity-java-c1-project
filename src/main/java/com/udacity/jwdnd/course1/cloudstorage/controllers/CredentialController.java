package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    private final CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
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


}
