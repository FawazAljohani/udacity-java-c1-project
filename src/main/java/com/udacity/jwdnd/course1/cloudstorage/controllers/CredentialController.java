package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    private final CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping
    public String createCredential(@ModelAttribute("credentials") Credential credential, RedirectAttributes redirectAttrs){

        String returnMessage = null;

        if(credentialService.getCredential(credential.getCredentialId()) != null){
            redirectAttrs.addFlashAttribute("successMessage", "Credentials have been Updated Successfully");
            int rowsUpdated = credentialService.createCredential(credential);
            return "redirect:home";
        }

        int rowsAdded = credentialService.createCredential(credential);
        if(rowsAdded < 0){
            returnMessage = "Something wrong occurred while creating the credential, please try again";
        }

        if (returnMessage == null) {
            System.out.printf("created credential successfully");
            redirectAttrs.addFlashAttribute("successMessage", "Credentials were added Successfully!");
        } else {
            redirectAttrs.addFlashAttribute("errorMessage", returnMessage);
        }

        return "redirect:home";
    }

    @GetMapping("/delete/{credentialId}")
    public String deleteCredential(@PathVariable("credentialId") Integer credentialId, RedirectAttributes redirectAttrs){

        int rowsDeleted = credentialService.deleteCredential(credentialId);
        if(rowsDeleted < 0){
            redirectAttrs.addFlashAttribute("errorMessage" ,"Something wrong occurred while deleting the credential, please try again");
        } else {
            redirectAttrs.addFlashAttribute("successMessage", "Deleted Credentials Successfully!");
        }

        return "redirect:/home";
    }

    //    @GetMapping("/{credentialId}")
    //    public String viewCredential(@PathVariable("credentialId") Integer credentialId, Model model){
    //
    //        Credential cred = credentialService.getCredential(credentialId);
    //        String decryptedPassword = encryptionService.decryptValue(cred.getPassword(), cred.getKey());
    //        Credential newCredential = new Credential(cred.getCredentialId(), cred.getUrl(), cred.getUserName(), cred.getKey(), decryptedPassword, cred.getUserId());
    //
    //        System.out.println("dec pass is " + decryptedPassword);
    //
    //        return "redirect:home";
    //    }

}
