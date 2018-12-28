package com.project.shopcart.repository;

import com.project.shopcart.model.Quantity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuantityRepository extends CrudRepository<Quantity, Integer> {

}
