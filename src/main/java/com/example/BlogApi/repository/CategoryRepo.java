package com.example.BlogApi.repository;


import com.example.BlogApi.entity.BlogEntity;
import com.example.BlogApi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
