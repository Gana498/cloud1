package com.company.lms.cloud1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.company.lms.cloud1.service.CurrentUserService;


@Controller
public class HomeController {
    private final CurrentUserService currentUserService;
    public HomeController(CurrentUserService currentUserService) {
        this.currentUserService = currentUserService;
    }
        @GetMapping("/")
    public String getHomePage() {
        if(currentUserService.getCurrentUserRoles().contains("ROLE_ADMIN")) {
        return "adminhome";
    } // Returns the view name "home"
        return null; // Returns the view name "home"
    }
    
    @GetMapping("/manageusers")
    public String getManageUsersPage() {
        return "manageusers"; // Returns the view name "manageusers"
    }

   
    @GetMapping("/adminhome")
    public String getAdminHomePage() {
        return "adminhome"; // Returns the view name "adminhome"
    }
}
