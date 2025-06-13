package com.example.BlogApi.controller;


import com.example.BlogApi.entity.BlogEntity;
import com.example.BlogApi.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<BlogEntity> showAll(){
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
