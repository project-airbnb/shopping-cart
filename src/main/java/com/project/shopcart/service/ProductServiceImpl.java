package com.project.shopcart.service;

import com.project.shopcart.model.Product;
import com.project.shopcart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return this.productRepository.findById(id).get();
    }

    @Override
    public void save(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        this.productRepository.deleteById(id);
    }
}
