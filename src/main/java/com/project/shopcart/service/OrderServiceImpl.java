package com.project.shopcart.service;

import com.project.shopcart.model.Order;
import com.project.shopcart.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Iterable<Order> findAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public Order findById(Integer id) {
        return this.orderRepository.findById(id).get();
    }

    @Override
    public void save(Order order) {
        this.orderRepository.save(order);
    }

    @Override
    public void delete(Integer id) {
        this.orderRepository.deleteById(id);
    }
}
