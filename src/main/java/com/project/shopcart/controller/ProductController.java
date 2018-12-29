package com.project.shopcart.controller;

import com.project.shopcart.model.Product;
import com.project.shopcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Controller
public class ProductController {
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


    public List<String> covertStringToURL(List<Product> products) {

        List<String> strs = new ArrayList<String>();
        try {
            for (int i = 0; i < products.size(); i++) {
                String temp = Normalizer.normalize(products.get(i).getName(), Normalizer.Form.NFD);
                Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
                strs.add(pattern.matcher(temp).replaceAll("").toLowerCase()
                        .replaceAll(" ", "-").replaceAll("đ", "d"));
            }
            return strs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
