package org.example.service;


import org.example.dao.OrderRepository;
import org.example.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    public boolean createOrder(Order order) {
        return orderRepository.createOrder(order);
    }
}
