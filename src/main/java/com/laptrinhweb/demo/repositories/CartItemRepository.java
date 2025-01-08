package com.laptrinhweb.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laptrinhweb.demo.models.CartItem;
import com.laptrinhweb.demo.models.Product;
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    Optional<CartItem> findByProduct(Product product);
}

