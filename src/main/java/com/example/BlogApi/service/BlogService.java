package com.example.BlogApi.service;


import com.example.BlogApi.entity.BlogEntity;
import com.example.BlogApi.entity.UserEntity;
import com.example.BlogApi.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    BlogRepository repository;

    public Page<BlogEntity> getAllBlogs(Pageable pageable){
        return repository.findAll(pageable);
    }

    public BlogEntity saveBlog(BlogEntity entity){
        return repository.save(entity);
    }

    public void deleteById(Integer id){
        repository.deleteById(id);
    }

    @Transactional
    public void deleteAllBlogsByUser(UserEntity user) {
        repository.deleteByUser(user);
    }


    public Optional<BlogEntity> findById(Integer id) {
        return repository.findById(id);
    }

    public List<BlogEntity> findbytitle(String title){
        return repository.findByTitleContainingIgnoreCase(title);
    }

    public List<BlogEntity> searchByCategoryName(String categoryName) {
        return repository.findByCategory_CategoryIgnoreCase(categoryName);
    }


}