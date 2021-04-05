package com.springboot.crud.springbootcrud.service;

import com.springboot.crud.springbootcrud.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface Service {
    public void add(User user);
    public List<User> getList();
    public void deleteUser(Long id);
    public User show(Long id);
    public void update(User user, Long id);
}
