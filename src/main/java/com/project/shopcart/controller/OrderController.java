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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class OrderController extends GetUrlProduct{
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
        //get product
        List<Item> order_cart = (List<Item>) session.getAttribute("cart");
        Set<Product> products = new HashSet<>();
        for (int i = 0; i < order_cart.size(); i++) {
            products.add(order_cart.get(i).getProduct());
        }
        order.setProducts(products);
        order.setTotal_price((Integer) session.getAttribute("total_price"));
        this.orderService.save(order);
        model.addAttribute("order_success", order);

        //get quantity of one product
        for (int i = 0; i < order_cart.size(); i++) {
            int quantity_one_product = order_cart.get(i).getQuantity();
            Product this_product =  order_cart.get(i).getProduct();
            this.quantityService.save(new Quantity(quantity_one_product, this_product, order));
        }

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
        //get url product
        List<Product> products = new ArrayList<Product>(order.getProducts());
        model.addAttribute("url_product", covertStringToURL(products));
        return "cart/order-my-view";
    }
}
