package com.company.lms.cloud1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.lms.cloud1.service.SignUpService;


@Controller
public class SignUpController {
    @Autowired
    private SignUpService signUpService;//

    @GetMapping("/signup")
    public String showSignUpForm() {
        return "signup"; // Returns the signup.html view    
    }
    @PostMapping("/signup")
    public String processSignUp(
        @RequestParam String username,
        @RequestParam String password,
        @RequestParam String firstName,
        @RequestParam String lastName,
        @RequestParam String email,
        @RequestParam String phone,
        Model model
    ){
        try {
            signUpService.signUpUser(username, password, firstName, lastName, email, phone);    
            model.addAttribute("success", "User registered successfully, Please contact admin to enable your account!");
            return "signup"; // Redirect to login page after successful signup
        } catch (Exception e) {
            model.addAttribute("error", "Error during signup: " + e.getMessage());
            return "signup"; // Return to signup page with error message
        }
    }
}
