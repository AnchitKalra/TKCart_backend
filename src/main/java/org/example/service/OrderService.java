package org.example.service;


import org.example.dao.OrderRepository;
import org.example.model.Order;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    public Order createOrder(Order order) {
        return orderRepository.createOrder(order);
    }

    public List<Order> getOrders(User user) {
        return orderRepository.getOrdersList(user);
    }

    public Order getLastOrder(User user) {
        return orderRepository.getLastOrder(user);
    }
}
