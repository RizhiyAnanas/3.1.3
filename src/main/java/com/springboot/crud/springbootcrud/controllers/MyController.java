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

    @GetMapping("/new")
    public String createNewUser(Model model,Principal principal){
        User user =(User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("thisUser", user);
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "new";
    }


    @PostMapping()
    public String create(@ModelAttribute("user") User user, @RequestParam("role") String role){
        Set<Role> roles = new HashSet<>();
        if (role.equals("ROLE_ADMIN")){
                roles.add(roleService.getAllRoles().get(0));
        }else{
            roles.add(roleService.getAllRoles().get(1));
        }
        user.setRoles(roles);
        service.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", service.show(id));
        return "show";
    }


    @GetMapping
    public String showList(Model model, Principal principal){
        User user =(User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("thisUser", user);
        List<User> list = service.getList();
        model.addAttribute("users", list);
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "users";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", service.show(id));
        return "edit";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        service.deleteUser(id);
        return "redirect:/admin";
    }
    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user,@PathVariable("id") Long id, @RequestParam("role") String role ){
        Set<Role> roles = new HashSet<>();
        if (role.equals("ROLE_ADMIN")){
            roles.add(roleService.getAllRoles().get(0));
        }else{
            roles.add(roleService.getAllRoles().get(1));
        }
        user.setRoles(roles);
        service.update(user, id);
        return "redirect:/admin";
    }
}
