package com.springboot.crud.springbootcrud.service;




import com.springboot.crud.springbootcrud.model.User;
import com.springboot.crud.springbootcrud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
@Transactional
public class ServiceImpl implements Service {


    private UserRepository userRepository;
    @Autowired
    public ServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getList() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User show(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void update(User user, Long id) {
        User updateUser= userRepository.findById(id).orElse(null);
        updateUser.setAge(user.getAge());
        updateUser.setEmail(user.getEmail());
        updateUser.setName(user.getName());
        updateUser.setSurname(user.getSurname());
        if (user.getRoles()!= null){
        updateUser.setRoles(user.getRoles());}
        userRepository.saveAndFlush(updateUser);
    }
}
