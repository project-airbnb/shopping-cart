package com.project.shopcart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("admin/manager_user/list")
    public String listUser() {
        return "user/admin";
    }
}
