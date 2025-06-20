package com.example.BlogApi.service;

import com.example.BlogApi.entity.UserEntity;
import com.example.BlogApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JWTservice jwTservice;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    public UserEntity saveUser(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<UserEntity> findById(Integer id) {
        return userRepository.findById(id);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    public String verify(UserEntity user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

        if (authentication.isAuthenticated()){
            return jwTservice.generateToken(user.getUsername());

        }
        return "Invalid JWT Token";
    }
}