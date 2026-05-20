package com.deploy11.pertemuan11.controller;

import com.deploy11.pertemuan11.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final AuthService authService;

    public HomeController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        var userOpt = authService.getCurrentUser();
        if (userOpt.isPresent()) {
            model.addAttribute("user", userOpt.get());
            return "home";
        }
        return "redirect:/login";
    }
}
