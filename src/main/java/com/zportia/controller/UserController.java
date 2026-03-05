package com.zportia.controller;

import com.zportia.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String encodePasswords() {
        userService.encodePasswords();
        return "Passwords encoded successfully";
    }
}
