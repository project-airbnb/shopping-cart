package com.project.shopcart.service;

import com.project.shopcart.model.Order;

public interface OderService {
    Iterable<Order> findAll();

    Order findById(Integer id);

    void save(Order order);

    void delete(Integer id);
}
