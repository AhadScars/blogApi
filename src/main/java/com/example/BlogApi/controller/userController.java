package com.example.BlogApi.controller;


import com.example.BlogApi.entity.UserEntity;
import com.example.BlogApi.service.JWTservice;
import com.example.BlogApi.service.TokenBlacklistService;
import com.example.BlogApi.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blog/authuser")
public class userController {

    @Autowired
    private UserService userService;

    @Autowired
    TokenBlacklistService blacklistToken;

    @Autowired
    JWTservice jwTservice;

    @GetMapping("/showall")
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

    @PostMapping("/login")
    public String login(@RequestBody UserEntity user){
        return userService.verify(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            Date expiry = jwTservice.extractExpiration(token);
            blacklistToken.blacklistToken(token, expiry);
            return ResponseEntity.ok("Logout successful. Token is nw blacklist.");
        } else {
            return ResponseEntity.badRequest().body("Missing or invalid Authorization header");
        }
    }
}