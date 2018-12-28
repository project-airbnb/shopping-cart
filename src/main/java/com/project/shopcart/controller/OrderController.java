package com.project.shopcart.controller;

import com.project.shopcart.model.Item;
import com.project.shopcart.model.Order;
import com.project.shopcart.model.Product;
import com.project.shopcart.model.Quantity;
import com.project.shopcart.service.OrderService;
import com.project.shopcart.service.ProductService;
import com.project.shopcart.service.QuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private QuantityService quantityService;

    @GetMapping("/order")
    public String payment(Model model) {
        model.addAttribute("order", new Order());
        return "cart/order-detail";
    }

    @PostMapping("/order-success")
    public String confirmPayment(@ModelAttribute("order") Order order, Model model, HttpSession session) {
        //get product + quantity
        List<Item> order_cart = (List<Item>) session.getAttribute("cart");
        Set<Product> products = new HashSet<>();
        Set<Quantity> quantities = new HashSet<Quantity>();
        for (int i = 0; i < order_cart.size(); i++) {
            products.add(order_cart.get(i).getProduct());
        }
//        for (int i = 0; i < order_cart.size(); i++) {
//            this.quantityService.save(new Quantity(order_cart.get(i).getQuantity(), products));
//        }

        order.setProducts(products);
        order.setTotal_price((Integer) session.getAttribute("total_price"));
        this.orderService.save(order);
        model.addAttribute("order_success", order);
        session.removeAttribute("cart");
        session.removeAttribute("total_price");
        return "redirect:/my-order";
    }

    @GetMapping("/my-order")
    public String myOrder(Model model) {
        model.addAttribute("orders", this.orderService.findAll());
        return "cart/order-list";
    }

    @GetMapping("/my-order/{id}")
    public String myOrderDetail(@PathVariable("id") int id, Model model) {
        Order order = this.orderService.findById(id);
        model.addAttribute("order", order);
        return "cart/order-my-view";
    }
}
