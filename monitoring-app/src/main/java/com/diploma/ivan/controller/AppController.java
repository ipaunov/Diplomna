package com.diploma.ivan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

    @GetMapping("/admin/accessdenied")
    public String viewAdminDeniedPage(){
        return "accessdenied";
    }

    @RequestMapping("/admin/login")
    public String adminLoginPage(){
        return "admin/admin_login";
    }

    @GetMapping("/user/accessdenied")
    public String viewUserDeniedPage(){
        return "accessdenied";
    }

    @RequestMapping("/user/login")
    public String userLoginPage(){
        return "users/user_login";
    }

    @RequestMapping("/login")
    public String loginPage(){
        return "main_login";
    }

    @RequestMapping("/admin/dashboard")
    public String adminDashboard(){
        return "admin/admin_main_dashboard";
    }

    @RequestMapping("/admin/user")
    public String usersDashboard(){
        return "admin/user_dashboard";
    }

    @RequestMapping("/admin/configuration")
    public String adminConfigurationDashboard(){
        return "configurations_dashboard";
    }


    @RequestMapping("/user/dashboard")
    public String userConfigurationDashboard(){
        return "configurations_dashboard";
    }

}
