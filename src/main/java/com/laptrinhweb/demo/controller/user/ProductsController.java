package com.laptrinhweb.demo.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laptrinhweb.demo.models.Category;
import com.laptrinhweb.demo.models.Product;
import com.laptrinhweb.demo.services.CategoryServices;
import com.laptrinhweb.demo.services.ProductServices;

@Controller
@RequestMapping("/Product")
public class ProductsController {

    @Autowired
    private ProductServices productServices;
    @Autowired
    private CategoryServices categoryServices;

    @GetMapping
    public String List_product(Model model, @Param("keyword") String keyword,
    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        List<Category> categories = categoryServices.getAll();
        model.addAttribute("listCategory", categories);
        
        Page<Product> product = this.productServices.getAll(pageNo);
        if (keyword != null) {
            product = this.productServices.searchProduct(keyword, pageNo);
            model.addAttribute("keyword", keyword);
        } // search keyword in category name
        model.addAttribute("totalPage", product.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("product", product);
        return "products";
    }
}
