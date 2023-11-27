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
    public Cart saveCart(@RequestBody(required = true) Cart cart) {
        try {
            User user = cart.getUser();
            System.out.println("from ADDTOCART");
            System.out.println(user.getUsername());
            user = userService.findUser(user);
            System.out.println(user.getId());
            System.out.println(cart.getUser().getUsername());
            cart.setUser(user);
            Double totalValue = 0D;
            totalValue += (cart.getPrice() * cart.getQuantity());
            cart.setTotalValue(totalValue);
                return cartService.updateCart(cart);

        }catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/cart/getCart")
    @ResponseBody
    public List<Cart> getCart(@RequestBody(required = true) User user) {
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
    public boolean updateQuantity(@RequestBody()Object id) {
        try{
            System.out.println(id);
            String split[] = (id.toString()).split("=");
            String b = "";
            if(split.length == 2) {
                b = split[1].split("}")[0];
                Integer Id = Integer.parseInt(b);
                boolean flag = cartService.updateQuantity(Id, 1);
                System.out.println(flag);
                return flag;
            }
            else {
                b = split[1].split(",")[0];
                Integer Id = Integer.parseInt(b);
                System.out.println(Id);
                boolean flag = cartService.updateQuantity(Id, 0);
                System.out.println(flag);
                return flag;
            }

        }
        catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
