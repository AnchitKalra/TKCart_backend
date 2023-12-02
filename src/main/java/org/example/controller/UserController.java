package org.example.controller;

import org.example.model.User;
import org.example.service.ProductsService;
import org.example.service.UserService;
import org.example.utils.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;


import java.time.ZonedDateTime;

import java.util.UUID;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@CrossOrigin(origins = "*")
public class UserController {


    @Autowired
    UserService userService;
    @Autowired
    ProductsService productsService;

    @RequestMapping(method = RequestMethod.POST, value = "/user/signup")
    @ResponseBody
    public ResponseEntity<User> signup( @RequestBody(required = true) User user )

    {
        try {
            User reUser = userService.signup(user);
            if (reUser.getId() != null) {
                return new ResponseEntity<>(reUser, HttpStatus.CREATED);
            }
        }catch (Exception e) {
            System.out.println(e);

        }
        return new ResponseEntity<>(null);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user/login")
    @ResponseBody
    public ResponseEntity<User> login(  @RequestBody(required = true) User user) {

        try{
            User existingUser = userService.login(user);
            String password = existingUser.getPassword();
            JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(password);
            String Uuid = UUID.randomUUID().toString();
            ZonedDateTime now = ZonedDateTime.now();
            ZonedDateTime expiresAt = ZonedDateTime.now().plusHours(2);
            String token = jwtTokenProvider.generateToken(Uuid, now, expiresAt);
            existingUser.setAccessToken(token);
            boolean flag = userService.persistUser(existingUser);
            if(flag) {
                System.out.println("**************OK****************************");
            }
            else{
                System.out.println("**************NO****************************");
            }

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("access-token", token);
            httpHeaders.add("Access-Control-Expose-Headers", "*");
            return new ResponseEntity<>(existingUser, httpHeaders, HttpStatus.OK);
        }
    catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/user/loginWithToken")
    public ResponseEntity<User> loginWithToken(@RequestHeader(required = true, name = "access-token") String authorization) {
        User reUser = new User();
        System.out.println("***********************************************************************");
        System.out.println(authorization);
        try {
            reUser = userService.loginWithToken(authorization);
            System.out.println("*************from login with token****************");
            System.out.println(reUser.getUsername());
            return new ResponseEntity<>(reUser, HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    @RequestMapping(value = "/user/logout", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> logout(HttpSession session) {

        System.out.println("logout");
        session.invalidate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("access-token", "");
        httpHeaders.add("Access-Control-Expose-Headers", "*");
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
    }
}
