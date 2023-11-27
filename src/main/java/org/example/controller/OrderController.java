package org.example.controller;


import org.example.model.Cart;
import org.example.model.Order;
import org.example.model.User;
import org.example.service.OrderService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;

    @RequestMapping(method = RequestMethod.POST, value = "/order/checkout")
    @ResponseBody
    public String createOrder(@RequestBody() Order order) {
        System.out.println("*************from create order***********");
        List<Cart> cartList = order.getCartList();
        for(Cart c : cartList) {
            System.out.println(c.getTitle());
        }
        String randomString = "ORDER" + Math.random() + "XXYY";
        order.setOrderId(randomString);
        User user = userService.findUser(order.getUser());
        order.setUser(user);
        if(orderService.createOrder(order)) {

            return randomString;
        }


        return "";
    }
}
