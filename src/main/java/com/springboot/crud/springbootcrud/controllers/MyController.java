package com.springboot.crud.springbootcrud.controllers;

import com.springboot.crud.springbootcrud.service.RoleService;
import com.springboot.crud.springbootcrud.service.Service;
import com.springboot.crud.springbootcrud.service.UserService;
import com.springboot.crud.springbootcrud.model.Role;
import com.springboot.crud.springbootcrud.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("admin")
public class MyController {
    private final RoleService roleService;
    final private Service service;
    private final UserService userService;

    public MyController(Service service, RoleService roleService, UserService userService) {
        this.service = service;
        this.roleService = roleService;
        this.userService = userService;
    }
    @GetMapping
    public String showList(Model model, Principal principal){
        User user =(User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("thisUser", user);
        model.addAttribute("user", new User());
        model.addAttribute("users", service.getList());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "users";
    }
}
