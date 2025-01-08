package com.laptrinhweb.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.laptrinhweb.demo.models.CartItem;
import com.laptrinhweb.demo.models.Product;

@Service
public interface CartService {
    List<CartItem> getAllCartItems();
    void addToCart(Product product, int quantity);
    void removeFromCart(Integer cartItemId);
    void updateQuantity(Integer cartItemId, int newQuantity);
    
}