package com.laptrinhweb.demo.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.laptrinhweb.demo.models.Category;

public interface CategoryServices {
    List<Category> getAll();
    Boolean create(Category category);
    Category findById(Integer id);
    Boolean update(Category category);
    Boolean delete(Integer id);
    List<Category> searchCategory(String keyword);

    Page<Category> getAll(Integer pageNo);
    Page<Category> searchCategory(String keyword, Integer pageNo);

}
