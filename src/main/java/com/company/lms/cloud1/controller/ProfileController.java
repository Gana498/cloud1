package com.company.lms.cloud1.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.company.lms.cloud1.model.User;
import com.company.lms.cloud1.model.UserDetails;
import com.company.lms.cloud1.repository.UserDetailsRepository;
import com.company.lms.cloud1.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
@Controller
public class ProfileController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDetailsRepository userDetailsRepository;
    
     @GetMapping("/profile")
    public String showProfile(Model model, Authentication authentication) {
        String username = authentication.getName();
        model.addAttribute("username", username);
        User user = userRepository.findByUsername(username);
        UserDetails userDetails = null;
        if (user != null) {
            userDetails = userDetailsRepository.findByUserId(user.getId());
        }
        model.addAttribute("userDetails", userDetails);
        return "profile"; // Returns the view name "profile"
    }
}
