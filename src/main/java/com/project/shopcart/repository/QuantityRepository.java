package com.project.shopcart.repository;

import com.project.shopcart.model.Quantity;
import org.springframework.data.repository.CrudRepository;

public interface QuantityRepository extends CrudRepository<Quantity, Integer> {
}
