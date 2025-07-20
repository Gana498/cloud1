package com.company.lms.cloud1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    
    // This controller can be used to handle requests to the home page
    @GetMapping("/adminhome")
    public String getAdminHome() {
        return "adminhome"; // Returns the view name "adminhome"
    }
    
    @GetMapping("/manageusers")
    public String getManageUsersPage() {
        return "manageusers"; // Returns the view name "manageusers"
    }
}
