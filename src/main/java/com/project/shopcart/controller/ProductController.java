package com.project.shopcart.controller;

import com.project.shopcart.model.Order;
import com.project.shopcart.model.Product;
import com.project.shopcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController extends GetUrlProduct {
    @Autowired
    private ProductService productService;

    @GetMapping
    public String listProduct(Model model) {
        Iterable<Product> products = this.productService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("name_urls", covertStringToURL((List<Product>) products));
        return "product/list-product";
    }

    @GetMapping("/{name}-{id}")
    public String detailProduct(@PathVariable("id") int id, Model model) {
        Product product = this.productService.findById(id);
        model.addAttribute("product", product);
        return "product/detail-product";
    }

    @GetMapping("/create-product")
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product/create-product";
    }

    @PostMapping("/create-product")
    public String saveCreateProduct(@ModelAttribute("product") Product product) {
        product.setUrl(covertStringToURL(product.getName()));
        this.productService.save(product);
        return "redirect:/my-list-product";
    }

    @GetMapping("/edit-product/{id}")
    public String editProduct(@PathVariable("id")int id, Model model) {
        model.addAttribute("product", this.productService.findById(id));
        return "product/edit-product";
    }

    @PostMapping("/edit-product")
    public String saveEditProduct(@ModelAttribute("product") Product product) {
        product.setUrl(covertStringToURL(product.getName()));
        this.productService.save(product);
        return "redirect:/my-list-product";
    }

    @GetMapping("/my-list-product")
    public String myListProduct(Model model) {
        model.addAttribute("products", this.productService.findAll());
        return "product/my-list-product";
    }

    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable("id")int id) {
        this.productService.delete(id);
        return "product/edit-product";
    }

}
