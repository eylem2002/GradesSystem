package com.example.systemspring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model)
    {
        model.addAttribute("id",LoginController.authenticationService.getAuthenticatedId());
        return "home-page";
    }
    @GetMapping("/logout")
    public String logout()
    {
        LoginController.authenticationService.setAuthenticated(false);
        return "redirect:/login";
    }
}
