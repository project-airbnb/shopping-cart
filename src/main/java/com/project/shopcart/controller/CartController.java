package com.project.shopcart.controller;

import com.project.shopcart.model.Item;
import com.project.shopcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private ProductService productService;

    @GetMapping("/cart")
    public String myCart() {
        return "cart/list-cart";
    }

    @GetMapping("/cart/buy/{id}")
    public String buy(@PathVariable("id") int id, HttpSession session) {
        List<Item> cart = new ArrayList<Item>();
        if (session.getAttribute("cart") == null) {
            cart.add(new Item(this.productService.findById(id), 1));
        } else {
            //kiem tra da dat san pham nay chua
            cart = (List<Item>) session.getAttribute("cart");
            int index = isExist(id, cart);
            if (index == -1) {
                cart.add(new Item(this.productService.findById(id), 1));
            } else {
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);
            }
        }
        session.setAttribute("cart", cart);
        session.setAttribute("total_price", priceAll(cart));
        return "redirect:/";
    }

    private int isExist(int id, List<Item> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getId() == id) {
                return i;
            }
        }
        return -1;
    }

    private int priceAll(List<Item> cart) {
        int total_price = 0;
        if (cart.size() > 0) {
            for (int i = 0; i < cart.size(); i++) {
                total_price += (cart.get(i).getQuantity() * cart.get(i).getProduct().getPrice());
            }
        }
        return total_price;
    }

    @GetMapping("/cart/remove/{id}")
    public String delete(@PathVariable("id") int id, HttpSession session) {
        if (session.getAttribute("cart") != null) {
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            int index = isExist(id, cart);
            if (index != -1) {
                cart.remove(index);
                session.setAttribute("cart", cart);
                session.setAttribute("total_price", priceAll(cart));
            }
        }
        return "redirect:/cart";
    }

    @PostMapping("/cart/update")
    public String update(HttpServletRequest request, HttpSession session) {
        String[] quantities = request.getParameterValues("quantity");
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        for (int i = 0; i < cart.size(); i++) {
            cart.get(i).setQuantity(Integer.parseInt(quantities[i]));
            session.setAttribute("total_price", priceAll(cart));
        }
        System.out.println(quantities.length);
        return "redirect:/cart";
    }
}
