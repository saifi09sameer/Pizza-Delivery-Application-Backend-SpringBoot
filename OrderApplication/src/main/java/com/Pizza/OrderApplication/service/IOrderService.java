package com.Pizza.OrderApplication.service;

import com.Pizza.OrderApplication.domain.Order;
import com.Pizza.OrderApplication.exception.OrderNotFoundException;

import java.util.List;
import java.util.Map;

public interface IOrderService {
    Order addNewOrder(Order order);
    List<Order> getAllOrders(String userEmail) throws OrderNotFoundException;

    String getTotalAmount(String userEmail);
    Map<String,Integer> getAllUsersAmount();
    String getTotalAllUsersAmount();
}
