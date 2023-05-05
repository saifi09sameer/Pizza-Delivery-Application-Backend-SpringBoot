package com.Pizza.OrderApplication.repository;

import com.Pizza.OrderApplication.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends MongoRepository<Order,Integer> {
    List<Order> findAllByUserEmail(String userEmail);
}
