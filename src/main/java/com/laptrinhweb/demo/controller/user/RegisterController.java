package com.laptrinhweb.demo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laptrinhweb.demo.models.Customer;
import com.laptrinhweb.demo.services.CustomerService;

@Controller
 @RequestMapping("/register")
public class RegisterController {
    
    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public String getRegister(@ModelAttribute("Customer") Customer customer){
        return "register";
    }
    @PostMapping()
    public String saveCustomerAccount(@ModelAttribute("Customer") Customer customer,Model model) {
        customerService.createCustomer(customer);
        model.addAttribute("message", "Register successful!");
        return "login";
    }
}
