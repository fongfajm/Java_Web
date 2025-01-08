package com.laptrinhweb.demo.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.laptrinhweb.demo.models.Category;
import com.laptrinhweb.demo.models.Product;
import com.laptrinhweb.demo.services.CategoryServices;
import com.laptrinhweb.demo.services.ProductServices;
import com.laptrinhweb.demo.services.StorageService;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
    @Autowired
    private ProductServices productServices;
    @Autowired
    private CategoryServices categoryServices;
    @Autowired
    private StorageService storageService;

    @GetMapping
    public String List_product(Model model, @Param("keyword") String keyword,
            @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<Product> product = this.productServices.getAll(pageNo);
        if (keyword != null) {
            product = this.productServices.searchProduct(keyword, pageNo);
            model.addAttribute("keyword", keyword);
        } // search keyword in category name
        model.addAttribute("totalPage", product.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("product", product);

        return "admin/product/index";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        List<Category> categories = this.categoryServices.getAll();
        model.addAttribute("categories", categories);
        return "admin/product/add-product";
    }

    @PostMapping("/add")
    public String handleAddProduct(@ModelAttribute("product") Product product,
            @RequestParam("fileImage") MultipartFile file) {
        this.storageService.store(file);
        String fileName = file.getOriginalFilename();
        product.setImageFileName(fileName);
        if (productServices.create(product)) {
            return "redirect:/admin/product";
        }
        return "admin/product/add-product";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") Integer id, Model model) {
        Product product = productServices.findById(id);
        if (product == null) {
            return "redirect:/admin/product"; // Redirect if product not found
        }
        model.addAttribute("product", product);
        return "admin/product/edit-product";
    }

    @PostMapping("/edit")
    public String handleEditProduct(@ModelAttribute("product") Product product) {
        if (productServices.update(product)) {
            return "redirect:/admin/product";
        }
        return "admin/product/edit-product";
    }

    @GetMapping("/delete/{id}")
    public String handleDeleteProduct(@PathVariable("id") Integer id) {
        productServices.delete(id); // Assuming delete handles invalid IDs gracefully
        return "redirect:/admin/product";
    }
}
