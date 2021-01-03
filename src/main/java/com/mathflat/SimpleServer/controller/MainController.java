package com.mathflat.SimpleServer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("main")
    public String Hello(Model model) {
        model.addAttribute("data", "mathFlat");
        return "main";
    }
}
