package org.example.controller;


import org.example.model.Cart;
import org.example.model.User;
import org.example.service.CartService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;


    @RequestMapping(method = RequestMethod.POST, value = "/cart/addToCart")
    @ResponseBody
    public Cart saveCart( @RequestBody(required = true) Cart cart) {
        try {
            User user = userService.findUser(cart.getUser());
            System.out.println(user.getId());
            System.out.println(cart.getUser().getUsername());
            cart.setUser(user);
            Double totalValue = 0D;
            totalValue += (cart.getPrice() * cart.getQuantity());
            cart.setTotalValue(totalValue);
            cart.setImage("");
            return cartService.updateCart(cart);


        }catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/cart/getCart")
    @ResponseBody
    public List<Cart> getCart( @RequestBody(required = true) User user) {
        try {
            System.out.println(user.getUsername());
            user = userService.findUser(user);
            System.out.println(user.getUsername());
            Integer userId = user.getId();
            System.out.println(userId);
            return cartService.getCart(user);

        }catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/cart/clearCart")
    @ResponseBody
    public boolean clearCart(@RequestBody() User user) {
        try {
            user = userService.findUser(user);
            List<Cart> cart = cartService.getCart(user);
             return cartService.clearCart(cart);
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/cart/updateQuantity")
    @ResponseBody
    public boolean updateQuantity(@RequestBody() Cart cart) {
        try {
           Integer id = (cart.getId());
           if(cart.getPlusMinus() != null) {
               String plusMinus = cart.getPlusMinus();
               System.out.println(plusMinus);
               return cartService.updateQuantity(id, 0);
           }
          else{
              return cartService.updateQuantity(id, 1);
           }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
