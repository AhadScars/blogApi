package com.example.BlogApi.service;


import com.example.BlogApi.entity.BlogEntity;
import com.example.BlogApi.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    BlogRepository repository;

    public List<BlogEntity> getAllBlogs(){
        return repository.findAll();
    }

    public BlogEntity saveBlog(BlogEntity entity){
        return repository.save(entity);
    }

    public void deleteById(Integer id){
       repository.deleteById(id);
    }

    public void deleteAll (){
        repository.deleteAll();
    }

}
