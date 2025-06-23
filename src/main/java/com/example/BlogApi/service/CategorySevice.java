package com.example.BlogApi.service;

import com.example.BlogApi.entity.Category;
import com.example.BlogApi.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorySevice {
    @Autowired
    CategoryRepo categoryRepo;

    public List<Category> findAll(){
        return categoryRepo.findAll();
    }

    public Category addCategory(Category category){
        return categoryRepo.save(category);
    }


}
