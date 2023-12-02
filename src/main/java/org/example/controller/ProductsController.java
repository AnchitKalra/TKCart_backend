package org.example.controller;

import org.example.model.Products;
import org.example.service.ProductsService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
@CrossOrigin(origins = "*")
public class ProductsController {

    @Autowired
    ProductsService productsService;

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/products/getProducts")
    public void getProducts(@RequestBody() List<Products> list) {
        productsService.saveProducts(list);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/products/fetchProducts")
    @ResponseBody()
    public List<Products> fetchProducts() {
        return productsService.fetchProducts();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/products/getOption")
    @ResponseBody
    public ResponseEntity<Products> getOptionProduct(@RequestBody(required = true) Products title) {
        Products products = new Products();
        try{
            System.out.println(title);
        List<Products> productsList = fetchProducts();

        for(Products p : productsList) {
            if(p.getTitle().equals(title.getTitle())) {
                products = p;
                break;
            }
        }
        }catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);

    }

}

/*

 private String convertUploadedFileToBase64(byte bytes[])  {
        return Base64.getEncoder().encodeToString(bytes);
    }
  List<String> titleList = new ArrayList<>();
        titleList.add("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops");
        titleList.add("Mens Casual Premium Slim Fit T-Shirts ");
        titleList.add("Mens Cotton Jacket");
        titleList.add("Mens Casual Slim Fit");
        titleList.add("John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet");
        titleList.add("Solid Gold Petite Micropave ");
        titleList.add("White Gold Plated Princess");
        titleList.add("Pierced Owl Rose Gold Plated Stainless Steel Double");
        titleList.add("WD 2TB Elements Portable External Hard Drive - USB 3.0 ");
        titleList.add("SanDisk SSD PLUS 1TB Internal SSD - SATA III 6 Gb/s");
        titleList.add("Silicon Power 256GB SSD 3D NAND A55 SLC Cache Performance Boost SATA III 2.5");
        titleList.add("WD 4TB Gaming Drive Works with Playstation 4 Portable External Hard Drive");
        titleList.add("Acer SB220Q bi 21.5 inches Full HD (1920 x 1080) IPS Ultra-Thin");
        titleList.add("Samsung 49-Inch CHG90 144Hz Curved Gaming Monitor (LC49HG90DMNXZA) – Super Ultrawide Screen QLED ");
        titleList.add("BIYLACLESEN Women's 3-in-1 Snowboard Jacket Winter Coats");
        titleList.add("Lock and Love Women's Removable Hooded Faux Leather Moto Biker Jacket");
        titleList.add("Rain Jacket Women Windbreaker Striped Climbing Raincoats");
        titleList.add("MBJ Women's Solid Short Sleeve Boat Neck V ");
        titleList.add("Opna Women's Short Sleeve Moisture");
        titleList.add("DANVOUY Womens T Shirt Casual Cotton Short");
        A:for (Products p : productsList) {
           for(int i = 0; i < titleList.size(); i++) {
               try {
               if(p.getTitle().equals(titleList.get(i))) {
                   int index = i + 1;
                   String imagePath = "src/main/resources/" + index + ".jpg";
                   byte[] bytes = Files.readAllBytes(Paths.get(imagePath));
                   String text = convertUploadedFileToBase64(bytes);
                   p.setImages(text);
                   if(productsService.saveImage(p)) {
                       break;
                   }
                   else {
                       break A;
                   }

               }}catch (Exception e) {
                   System.out.println(e);
               }
           }
        }
        return null;
 */
