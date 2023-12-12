package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "cartId")
    @JsonProperty("cartId")
    private List<Cart> cartList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "userId")
    @JsonProperty("userId")
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "date")
    private Date date;



    private String orderId;

    public List<Cart> getCartList() {
        return cartList;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
