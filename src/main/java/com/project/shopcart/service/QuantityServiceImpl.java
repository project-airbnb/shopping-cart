package com.project.shopcart.service;

import com.project.shopcart.repository.QuantityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuantityServiceImpl {
    @Autowired
    private QuantityRepository quantityRepository;
}
