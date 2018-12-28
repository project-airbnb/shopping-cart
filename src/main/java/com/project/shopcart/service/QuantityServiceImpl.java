package com.project.shopcart.service;

import com.project.shopcart.model.Quantity;
import com.project.shopcart.repository.QuantityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuantityServiceImpl implements QuantityService {
    @Autowired
    private QuantityRepository quantityRepository;

    @Override
    public Iterable<Quantity> findAll() {
        return this.quantityRepository.findAll();
    }

    @Override
    public Quantity findById(Integer id) {
        return this.quantityRepository.findById(id).get();
    }

    @Override
    public void save(Quantity quantity) {
        this.quantityRepository.save(quantity);
    }

    @Override
    public void delete(Integer id) {
        this.quantityRepository.deleteById(id);
    }
}
