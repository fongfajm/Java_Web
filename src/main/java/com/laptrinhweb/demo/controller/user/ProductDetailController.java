package com.laptrinhweb.demo.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laptrinhweb.demo.models.Category;
import com.laptrinhweb.demo.models.Product;
import com.laptrinhweb.demo.services.CategoryServices;
import com.laptrinhweb.demo.services.ProductServices;

@Controller
@RequestMapping("/product-details")
public class ProductDetailController {

    @Autowired
    private CategoryServices categoryServices;
    @Autowired
    private ProductServices productServices;

    @GetMapping()
        public String Product(Model model, @Param("keyword") String keyword) {
            List<Category> categories = categoryServices.getAll();
            model.addAttribute("listCategory", categories);
            
            List<Product> product = productServices.getAll();
            if (keyword != null) {
                product = productServices.searchProduct(keyword);
            }
            model.addAttribute("product", product);
        return "products-details";
    }
}