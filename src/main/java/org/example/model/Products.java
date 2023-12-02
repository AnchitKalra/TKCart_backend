package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Blob;

@Entity
@Table(name = "products")
public class Products {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(name = "title")
    @Size(max = 1000)
    @JsonProperty("title")
    private String title;

    @Column(name = "price")
    @JsonProperty("price")
    private Double price;

    @Column(name = "description")
    @Size(max = 4000)
    @JsonProperty("description")
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


    @Column(name = "imageT", columnDefinition = "TEXT")
    @JsonProperty("imageT")
    private String images;


    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}

