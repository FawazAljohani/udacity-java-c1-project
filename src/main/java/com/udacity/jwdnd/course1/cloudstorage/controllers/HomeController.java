package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final UserService userService;
    private final NoteService noteService;
    private final CredentialService credentialService;
    private final EncryptionService encryptionService;

    public HomeController(UserService userService, NoteService noteService, CredentialService credentialService, EncryptionService encryptionService) {
        this.userService = userService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping
    public String getHome(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userService.getUser(auth.getName());
        Integer userId = user.getUserId();

        System.out.println("the user logged in id is: " + user.getUserId());

        model.addAttribute("userId", user.getUserId().toString());
        model.addAttribute("notes", noteService.getAllNotes(userId));
        model.addAttribute("credentials", credentialService.getAllCredentials(userId));
        model.addAttribute("encryptionService", this.encryptionService);

        return "home";
    }

}
