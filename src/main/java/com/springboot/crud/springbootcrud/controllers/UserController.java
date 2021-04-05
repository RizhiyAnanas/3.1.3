package com.springboot.crud.springbootcrud.controllers;

import com.springboot.crud.springbootcrud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserDetailsService userService;
    @Autowired
    public UserController(@Qualifier("userService") UserDetailsService userService) {
        this.userService = userService;
    }



    @GetMapping
    public String welcomeUser(Principal principal, Model model){
        User user =(User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "welcome";
    }
}
