package com.laptrinhweb.demo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class IncomeController {
   //income_page
    @RequestMapping("/income")
    public String income() {
        return "admin/income";
    }
}
