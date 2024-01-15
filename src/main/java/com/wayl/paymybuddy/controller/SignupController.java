package com.wayl.paymybuddy.controller;

import com.wayl.paymybuddy.model.DaoApplicationUser;
import com.wayl.paymybuddy.model.dto.ApplicationuserDto;
import com.wayl.paymybuddy.service.ApplicationUserService;
import com.wayl.paymybuddy.service.auth.ApplicationUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signup")
public class SignupController {
    private ApplicationUserService applicationuserService;

    @Autowired
    private ApplicationUserDetailsServiceImpl userDetailsService;

    @Autowired
    public SignupController(ApplicationUserService applicationuserService) {
        this.applicationuserService = applicationuserService;
    }

    @GetMapping
    public String signupView() {
        return "signup";
    }

    @PostMapping
    private String signupUser(@ModelAttribute ApplicationuserDto user, Model model, RedirectAttributes redirAttrs) {
        String signupError = null;
        DaoApplicationUser existsUser = applicationuserService.findByEmail(user.getEmail());
        if (existsUser != null) {
            signupError = "The email already exists";
        }
        if (signupError == null) {
            userDetailsService.save(user);
        }

        if (signupError == null) {
            redirAttrs.addFlashAttribute("message", "You've successfully signed up, please login.");
            return "redirect:/login";
        } else {
            model.addAttribute("signupError", true);
        }

        return "signup";

    }

}
