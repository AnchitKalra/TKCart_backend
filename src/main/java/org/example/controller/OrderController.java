package org.example.controller;


import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.example.model.Cart;
import org.example.model.Order;
import org.example.model.User;
import org.example.service.CartService;
import org.example.service.OrderService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;

    @Autowired
    CartService cartService;

    @RequestMapping(method = RequestMethod.POST, value = "/order/checkout")
    @ResponseBody
    public ResponseEntity<Order> createOrder(@RequestBody() Order order) {
        try {
            System.out.println("*************from create order***********");
            List<Cart> cartList = order.getCartList();
            for (Cart c : cartList) {
                System.out.println(c.getTitle());
            }
            String randomString = "ORDER" + Math.random() + "XXYY";
            order.setOrderId(randomString);
            User user = userService.findUser(order.getUser());
            order.setUser(user);
            Order order1 = orderService.createOrder(order);
            return new ResponseEntity<Order>(order1, HttpStatus.OK);
        }catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/order/getOrders")
    @ResponseBody
    public ResponseEntity<List<Cart>> getOrderIds(@RequestBody(required = true) User user1) {
        try {
            User user = userService.findUser(user1);
            List<Order> orderList = orderService.getOrders(user);
            List<Cart> resultList = new ArrayList<>();
            for(Order o : orderList) {
                List<Cart> c = (o.getCartList());
                for(Cart cart  : c) {
                    resultList.add(cart);
                }
                resultList.add(null);
            }
            return new ResponseEntity<>(resultList, HttpStatus.OK);
        }catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/order/getLastOrder")
    @ResponseBody
    public ResponseEntity<List<Cart>> getLastOrder(@RequestBody(required = true) User user1) {
        try{
           System.out.println(user1.getUsername());
        User user = userService.findUser(user1);
        System.out.println(user.getId()+ " " +  user.getPassword());
        Order order = orderService.getLastOrder(user);
           List<Cart> cartList = order.getCartList();
        return new ResponseEntity<>(cartList, HttpStatus.OK);
    }catch (Exception e) {
            System.out.println("ERROR");
            System.out.println(e);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
