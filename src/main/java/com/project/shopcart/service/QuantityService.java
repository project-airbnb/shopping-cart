package com.project.shopcart.service;

import com.project.shopcart.model.Quantity;

public interface QuantityService {

    Iterable<Quantity> findAll();

    Quantity findById(Integer id);

    void save(Quantity quantity);

    void delete(Integer id);
}
