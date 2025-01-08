package com.laptrinhweb.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.laptrinhweb.demo.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
            @Query("SELECT c FROM Product c WHERE c.name LIKE %?1%")
    List<Product> searchProduct(String keyword);
}
