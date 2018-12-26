package com.project.shopcart.service;

import com.project.shopcart.model.Product;

public interface ProductService {

    Iterable<Product> findAll();

    Product findById(Integer id);

    void save(Product product);

    void delete(Integer id);
}
