package com.example.building.module.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.building.security.service.SecurityService;

/**
 * LoginController
 */
@Controller
public class LoginController {

    private static final String LOGIN_TEMPLATE = "/module/login/login";

    @Autowired
    private SecurityService securityService;

    // Login form
    @RequestMapping("/login")
    public String login(Model model, String error, String logout) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return LOGIN_TEMPLATE;
    }

    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return LOGIN_TEMPLATE;
    }
}
