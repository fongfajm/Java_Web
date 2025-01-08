package com.laptrinhweb.demo.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.laptrinhweb.demo.models.Product;

public interface ProductServices {
    List<Product> getAll();
    Boolean create(Product product);
    Product findById(Integer id);
    Boolean update(Product product);
    Boolean delete(Integer id);
    Product getProductById(Integer id);
    List<Product> searchProduct(String keyword);

    Page<Product> getAll(Integer pageNo);
    Page<Product> searchProduct(String keyword, Integer pageNo);

}
