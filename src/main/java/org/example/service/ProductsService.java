package org.example.service;

import org.example.dao.ProductsRepository;
import org.example.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsService {

    @Autowired
    ProductsRepository repository;
    public void saveProducts(List<Products> productsList) {
        for (Products p : productsList) {
            if(!repository.saveProducts(p)){
                break;
            }
        }
    }
    public void getProducts() {
        List<Products> list = repository.getProducts();
        System.out.println(list);
        List<Products> productsList = new ArrayList<>();
        List<Products> duplicateList = new ArrayList<>();
        for(Products p : list) {
            //System.out.println("*************************************************************************************");
            for (Products p1: productsList) {
                if(p.getTitle().equals(p1.getTitle())) {
                    duplicateList.add(p);
                    break;
                }
            }
            if(!duplicateList.contains(p)) {
                productsList.add(p);
            }
        }

        System.out.println(duplicateList);
        System.out.println(productsList);
        for(Products p : duplicateList) {
            System.out.println(p.getTitle());
            if(!repository.deleteProducts(p)) {
                break;
            }
        }
    }
    public List<Products> fetchProducts() {
        List<Products> productsList = repository.getProducts();
        for(Products p : productsList) {
            String s = p.getImages();
            p.setImage(s);
        }
        return productsList;
    }

    public boolean saveImage(Products products) {
        return repository.saveImages(products);
    }
}
