package com.springboot.crud.springbootcrud.controllers;

import com.springboot.crud.springbootcrud.model.Role;
import com.springboot.crud.springbootcrud.model.User;
import com.springboot.crud.springbootcrud.service.RoleService;
import com.springboot.crud.springbootcrud.service.Service;
import com.springboot.crud.springbootcrud.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

    private final RoleService roleService;
    final private Service service;
    private final UserService userService;

    public RestController(Service service, RoleService roleService, UserService userService) {
        this.service = service;
        this.roleService = roleService;
        this.userService = userService;
    }


    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUserList(){
        List<User> list = service.getList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addUser(User user, String role){
        Set<Role> roles = new HashSet<>();
        if (role.equals("ROLE_ADMIN")){
            roles.add(roleService.getAllRoles().get(0));
        }else{
            roles.add(roleService.getAllRoles().get(1));
        }
        user.setRoles(roles);
        service.add(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        if (service.show(id) == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        service.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK); }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable("id") Long id, String role) {
        if (service.show(id) == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Set<Role> roles = new HashSet<>();
        if (role.equals("ROLE_ADMIN")){
            roles.add(roleService.getAllRoles().get(0));
        }else{
            roles.add(roleService.getAllRoles().get(1));
        }
        user.setRoles(roles);
        service.update(user, id);
        return new ResponseEntity<>(service.show(id), HttpStatus.OK);
    }
}
