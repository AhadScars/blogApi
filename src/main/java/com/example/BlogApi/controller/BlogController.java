package com.example.BlogApi.controller;


import com.example.BlogApi.entity.BlogEntity;
import com.example.BlogApi.entity.UserEntity;
import com.example.BlogApi.service.BlogService;
import com.example.BlogApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/blog")
public class BlogController {

    @Autowired
    BlogService service;

    @PostMapping("/add")
    public BlogEntity addBlog(@RequestBody BlogEntity entity){
        return service.saveBlog(entity);
    }

    @GetMapping("/showblogs")
    public List<BlogEntity> getAllBlogs(){
        return service.getAllBlogs();
    }

    @DeleteMapping("/deleteall")
    public void deleteAll(){
        service.deleteAll();
    }
    @DeleteMapping("/delete/id/{id}")
    public void deleteById(@PathVariable Integer id){
         service.deleteById(id);
    }
}
