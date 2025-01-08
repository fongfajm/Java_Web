package com.laptrinhweb.demo.controller.admin;

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

import com.laptrinhweb.demo.models.Category;
import com.laptrinhweb.demo.services.CategoryServices;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    // Add dependency for categoryServices to access to data in database
    @Autowired
    private CategoryServices categoryServices;
    @GetMapping
    public String listCategories(Model model, @Param("keyword") String keyword,
            @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<Category> categories = this.categoryServices.getAll(pageNo);

        if (keyword != null) {
            categories = this.categoryServices.searchCategory(keyword, pageNo);
            model.addAttribute("keyword", keyword);
        } // search keyword in category name
        model.addAttribute("totalPage", categories.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listCategory", categories);
        return "admin/category/index";
    }

    @GetMapping("/add")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category/add-category";
    }

    @PostMapping("/add")
    public String handleAddCategory(@ModelAttribute("category") Category category) {
        if (categoryServices.create(category)) {
            return "redirect:/admin/category";
        }
        return "admin/category/add-category";
    }

    @GetMapping("/edit/{id}")
    public String showEditCategoryForm(@PathVariable("id") Integer id, Model model) {
        Category category = categoryServices.findById(id);
        if (category == null) {
            return "redirect:/admin/category"; // Redirect if category not found
        }
        model.addAttribute("category", category);
        return "admin/category/edit-category";
    }

    @PostMapping("/edit")
    public String handleEditCategory(@ModelAttribute("category") Category category) {
        if (categoryServices.update(category)) {
            return "redirect:/admin/category";
        }
        return "admin/category/edit-category";
    }

    @GetMapping("/delete/{id}")
    public String handleDeleteCategory(@PathVariable("id") Integer id) {
        categoryServices.delete(id); // Assuming delete handles invalid IDs gracefully
        return "redirect:/admin/category";
    }
}
