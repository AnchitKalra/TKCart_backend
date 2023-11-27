package org.example.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(name = "title")
    @JsonProperty("title")
    private String title;

    @Column(name = "price")
    @JsonProperty("price")
    private Double price;


    @Column(name = "description")
    @JsonProperty("description")
    @Size(max = 3000)
    private String description;

    @Column(name = "category")
    @JsonProperty("category")
    private String category;

    @Column(name = "image")
    @JsonProperty("image")
    private String image;

    @Column(name = "rate")
    @JsonProperty("rate")
    private Double rate;

    @Column(name = "count")
    @JsonProperty("count")
    private Integer count;

    @Column(name = "quantity")
    @JsonProperty("quantity")
    private Integer quantity;


    @Column(name = "totalValue")
    @JsonProperty("totalValue")
    private Double totalValue;



    @OneToOne
    @JoinColumn(name = "userId")
    @JsonProperty("userId")
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }
}