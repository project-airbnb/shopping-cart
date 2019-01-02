package com.project.shopcart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/403")
    public String errors() {
        return "error/errors";
    }
}
