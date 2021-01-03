package com.mathflat.SimpleServer.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@ControllerAdvice
@Controller
public class CustomErrorController extends RuntimeException {
    @GetMapping("error")
    @ExceptionHandler(Exception.class)
    public String except(Exception ex, Model model) {
        model.addAttribute("msg", ex.getMessage());
        model.addAttribute("timestamp", new Date());
        return "error";
    }
}