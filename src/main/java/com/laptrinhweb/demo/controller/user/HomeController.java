package com.laptrinhweb.demo.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {
    @GetMapping("/home")
    public String Home() {
        return"index";
}
    // Thêm các phương thức xử lý các trang khác tại đây (About, Shop, Blog, Contact,...)
    @GetMapping("/blog")
    public String Blog() {
        return "blog";
    }
    @GetMapping("/contact")
    public String Contact() {
        return "contact";
    }
}