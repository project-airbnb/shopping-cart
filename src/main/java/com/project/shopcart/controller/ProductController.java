package com.project.shopcart.controller;

import com.project.shopcart.model.Product;
import com.project.shopcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public String listProduct(Model model) {
        Iterable<Product> products = this.productService.findAll();
        model.addAttribute("products", products);

        return "product/list-product";
    }

}
