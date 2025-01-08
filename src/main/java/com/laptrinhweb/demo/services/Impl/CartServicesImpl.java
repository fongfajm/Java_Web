package com.laptrinhweb.demo.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhweb.demo.models.CartItem;
import com.laptrinhweb.demo.models.Product;
import com.laptrinhweb.demo.repositories.CartItemRepository;
import com.laptrinhweb.demo.services.CartService;

@Service
public class CartServicesImpl implements CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    @Override
    public void addToCart(Product product, int quantity) {
        // Check if the product already exists in the cart
        CartItem cartItem = cartItemRepository.findByProduct(product).orElse(new CartItem());
        cartItem.setProduct(product);
        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        cartItemRepository.save(cartItem);
    }

    @Override
    public void removeFromCart(Integer cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public void updateQuantity(Integer cartItemId, int newQuantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> 
            new IllegalArgumentException("Cart item not found"));
        cartItem.setQuantity(newQuantity);
        cartItemRepository.save(cartItem);
    }
}
