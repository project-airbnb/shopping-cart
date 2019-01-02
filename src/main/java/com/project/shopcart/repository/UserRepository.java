package com.project.shopcart.repository;

import com.project.shopcart.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    User findByUsername(String username);
}
