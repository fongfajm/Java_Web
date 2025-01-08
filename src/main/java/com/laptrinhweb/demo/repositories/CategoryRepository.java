package com.laptrinhweb.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.laptrinhweb.demo.models.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer>{
        @Query("SELECT c FROM Category c WHERE c.categoryName LIKE %?1%")
    List<Category> searchCategory(String keyword);
}