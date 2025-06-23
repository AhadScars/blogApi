package com.example.BlogApi.controller;

import com.example.BlogApi.entity.Category;
import com.example.BlogApi.service.CategorySevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/category")
@RestController
public class CategoryController {

    @Autowired
    CategorySevice service;

    @GetMapping("/show")
    public List<Category> findall(){
        return service.findAll();
    }

    @PostMapping("/save")
    public Category saveCategory(@RequestBody Category category){
        return service.addCategory(category);
    }

}
