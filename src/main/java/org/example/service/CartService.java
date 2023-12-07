package org.example.service;


import org.example.dao.CartRepository;
import org.example.model.Cart;
import org.example.model.Order;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;
    public Cart updateCart(Cart cart) {
        cartRepository.updateCart(cart);
        return cart;
    }

    public List<Cart> getCart(User userId) {
        return cartRepository.getCart(userId);
    }


    public boolean clearCart(List<Cart> cart) {
        return cartRepository.clearCart(cart);

    }


    public boolean updateQuantity(Integer id, Integer plusMinus) {
        return cartRepository.updateQuantity(id, plusMinus);
    }

    public List<Cart> getCartList(List<Order> orderList, User user)  {
        return cartRepository.getCartList(orderList, user);
    }




}
