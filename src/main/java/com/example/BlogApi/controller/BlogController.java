package com.example.BlogApi.controller;


import com.example.BlogApi.entity.BlogEntity;
import com.example.BlogApi.entity.UserEntity;
import com.example.BlogApi.service.BlogService;
import com.example.BlogApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth/blog")
public class BlogController {

    @Autowired
    BlogService service;
    @Autowired
    UserService userService;

    @PostMapping("/add")
    public BlogEntity addBlog(@RequestBody BlogEntity entity) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByUsername(username);
        entity.setUser(user);
        return service.saveBlog(entity);
    }


    @GetMapping("/showblogs")
    public List<BlogEntity> getAllBlogs(){
        return service.getAllBlogs();
    }



@Transactional
@DeleteMapping("/deleteall")
    public String deleteAllBlogsByCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByUsername(username);

        service.deleteAllBlogsByUser(user);
        return "All blogs deleted for user: " + username;
    }




    @DeleteMapping("/delete/id/{id}")
    public String deleteById(@PathVariable Integer id) {
        Optional<BlogEntity> optionalBlog = service.findById(id);
        if (optionalBlog.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog not found");
        }

        BlogEntity blog = optionalBlog.get();
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!blog.getUser().getUsername().equals(currentUsername)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to delete this blog");
        }

        service.deleteById(id);
        return "Blog deleted successfully";

    }
}
