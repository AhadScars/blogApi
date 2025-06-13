package com.example.BlogApi.controller;


import com.example.BlogApi.entity.UserEntity;
import com.example.BlogApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blog/authuser")
public class userController {

    @Autowired
    private UserService userService;

    @GetMapping("/showAll")
    public List<UserEntity> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping("/register")
    public UserEntity registerUser(@RequestBody UserEntity user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllUsers() {
        userService.deleteAll();
    }

    @GetMapping("/show/id/{id}")
    public Optional<UserEntity> getById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @GetMapping("/show/username/{username}")
    public UserEntity getByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
    }
}