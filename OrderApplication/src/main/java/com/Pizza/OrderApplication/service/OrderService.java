package com.Pizza.OrderApplication.service;

import com.Pizza.OrderApplication.domain.Order;
import com.Pizza.OrderApplication.exception.OrderNotFoundException;
import com.Pizza.OrderApplication.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class OrderService implements IOrderService{
    IOrderRepository iOrderRepository;
    @Autowired
    public OrderService(IOrderRepository iOrderRepository) {
        this.iOrderRepository = iOrderRepository;
    }

    @Override
    public Order addNewOrder(Order order) {
        order.setOrderID(generateRandomNumber());
        return iOrderRepository.insert(order);
    }

    @Override
    public List<Order> getAllOrders(String userEmail) throws OrderNotFoundException {
       List<Order> orderList =  iOrderRepository.findAllByUserEmail(userEmail);
       if (orderList.size()>0){
           return iOrderRepository.findAllByUserEmail(userEmail);
       }
       throw new OrderNotFoundException();

    }

    @Override
    public String getTotalAmount(String userEmail) {
        int totalAmount = 0;
        List<Order> orderList = iOrderRepository.findAllByUserEmail(userEmail);
        for (Order order:orderList) {
            totalAmount = totalAmount + order.getTotalAmount();
        }
        return String.valueOf(totalAmount);
    }

    @Override
    public Map<String, Integer> getAllUsersAmount() {
        List<Order> orderList = iOrderRepository.findAll();
        Map<String, Integer> usersAmount = new HashMap<>();
        for (Order order : orderList) {
            String userEmail = order.getUserEmail();
            int totalAmount = order.getTotalAmount();
            if (usersAmount.containsKey(userEmail)) {
                totalAmount += usersAmount.get(userEmail);
            }
            usersAmount.put(userEmail, totalAmount);
        }
        return usersAmount;
    }

    @Override
    public String getTotalAllUsersAmount() {
        List<Order> orderList = iOrderRepository.findAll();
        int totalAmount = 0;
        for (Order order:orderList) {
            totalAmount = totalAmount + order.getTotalAmount();
        }
        return String.valueOf(totalAmount);
    }

    public static int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(900000) + 100000;
    }


}
