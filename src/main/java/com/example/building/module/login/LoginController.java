package com.example.building.module.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * LoginController
 */
@Controller
public class LoginController {

    private static final String LOGIN_TEMPLATE = "/module/login/login";

    // Login form
    @RequestMapping("/login")
    public String login() {
        return LOGIN_TEMPLATE;
    }

    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return LOGIN_TEMPLATE;
    }
}
