package com.project.shopcart.controller;

import com.project.shopcart.model.Item;
import com.project.shopcart.model.Order;
import com.project.shopcart.model.Product;
import com.project.shopcart.service.OrderService;
import com.project.shopcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @GetMapping("/order")
    public String payment(Model model, HttpSession session) {
        List<Item> order_cart = (List<Item>) session.getAttribute("cart");
        session.setAttribute("order_cart", order_cart);
        model.addAttribute("order", new Order());
        return "cart/order-detail";
    }

    @PostMapping("/order-create")
    public String confirmPayment(@ModelAttribute("order") Order order, HttpSession session) {
        List<Item> order_cart = (List<Item>) session.getAttribute("order_cart");
        List<Product> products = new ArrayList<Product>();
        for (int i = 0; i < order_cart.size(); i++) {
            products.add(order_cart.get(i).getProduct());
        }
        order.setProducts(products);
        this.orderService.save(order);
        return "cart/order-success";
    }
}
