package org.example.controller;


import com.sun.org.apache.xpath.internal.operations.Bool;
import org.example.model.Cart;
import org.example.model.User;
import org.example.service.CartService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Cart> saveCart(@RequestBody(required = true) Cart cart) {
        try {
            User user = userService.findUser(cart.getUser());
            System.out.println(user.getId());
            System.out.println(cart.getUser().getUsername());
            cart.setUser(user);
            Double totalValue = 0D;
            totalValue += (cart.getPrice() * cart.getQuantity());
            cart.setTotalValue(totalValue);
            cart.setImage("");
            Cart cart1 =  cartService.updateCart(cart);
            return new ResponseEntity<>(cart1, HttpStatus.OK);


        }catch(Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/cart/getCart")
    @ResponseBody
    public ResponseEntity<List<Cart>>getCart( @RequestBody(required = true) User user) {
        try {
            System.out.println(user.getUsername());
            user = userService.findUser(user);
            System.out.println(user.getUsername());
            Integer userId = user.getId();
            System.out.println(userId);
            List<Cart> cartList =  cartService.getCart(user);
            return new ResponseEntity<>(cartList, HttpStatus.OK);

        }catch(Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/cart/clearCart")
    @ResponseBody
    public ResponseEntity<Boolean> clearCart(@RequestBody() User user) {
        try {
            user = userService.findUser(user);
            List<Cart> cart = cartService.getCart(user);
             Boolean flag =  cartService.clearCart(cart);
             if(flag) {
                 return new ResponseEntity<>(flag, HttpStatus.OK);
             }
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/cart/updateQuantity")
    @ResponseBody
    public ResponseEntity<Boolean> updateQuantity(@RequestBody() Cart cart) {
        try {
           Integer id = (cart.getId());
           if(cart.getPlusMinus() != null) {
               String plusMinus = cart.getPlusMinus();
               System.out.println(plusMinus);
               Boolean flag = cartService.updateQuantity(id, 0);
               if(flag) {
                   return new ResponseEntity<>(flag, HttpStatus.OK);
               }
           }
          else{
              Boolean flag = cartService.updateQuantity(id, 1);
              if(flag) {
                  return new ResponseEntity<>(flag, HttpStatus.OK);
              }
           }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}
