package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationService authService;

    public AuthController(UserService userService, AuthenticationService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping("/login")
    public String getLogin(@ModelAttribute("loginForm") User user , Model model){

        model.addAttribute("loginForm", user);
        return "login";
    }

    @GetMapping("/signup")
    public String getSignup(@ModelAttribute("signupForm") User user, Model model){
        model.addAttribute("signupForm", user);
        return "signup";
    }

    @PostMapping("/signup")
    public String postSignup(@ModelAttribute("signupForm") User user, Model model){

        String returnMessage = null;

        if(!userService.isUsernameAvailable(user.getUserName())){
            returnMessage = "The user name already exists please choose another name";
        }

        if (returnMessage == null) {
            int rowsAdded = userService.createUser(user);
            if (rowsAdded < 0) {
                returnMessage = "Some Errors occured, please try again";
            }
        }

        if (returnMessage == null) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", returnMessage);
        }

        return "signup";
    }

    @PostMapping("/logout")
    public String postLogout(Model model){
        return "login";
    }

}
