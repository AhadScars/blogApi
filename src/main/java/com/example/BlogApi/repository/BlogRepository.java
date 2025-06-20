package com.example.BlogApi.repository;

import com.example.BlogApi.entity.BlogEntity;
import com.example.BlogApi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<BlogEntity, Integer> {
    void deleteByUser(UserEntity user);
    List<BlogEntity> findByTitleContainingIgnoreCase(String title);

}


