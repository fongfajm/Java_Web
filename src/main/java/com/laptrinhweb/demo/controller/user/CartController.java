package com.laptrinhweb.demo.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laptrinhweb.demo.models.CartItem;
import com.laptrinhweb.demo.models.Product;
import com.laptrinhweb.demo.services.CartService;
import com.laptrinhweb.demo.services.ProductServices;

@Controller
@RequestMapping("/Cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductServices productService;

    @GetMapping
    public String viewCart(Model model) {
        List<CartItem> cartItems = cartService.getAllCartItems();
        model.addAttribute("cartItems", cartItems);
        return "shoping-cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Integer productId, @RequestParam int quantity) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            cartService.addToCart(product, quantity);
        }
        return "redirect:/Cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Integer cartItemId) {
        cartService.removeFromCart(cartItemId);
        return "redirect:/Cart";
    }

    @PostMapping("/update")
    public String updateQuantity(@RequestParam Integer cartItemId, @RequestParam int quantity) {
        cartService.updateQuantity(cartItemId, quantity);
        return "redirect:/Cart";
    }
}
