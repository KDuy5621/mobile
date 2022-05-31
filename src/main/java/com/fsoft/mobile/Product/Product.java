package com.fsoft.mobile.Product;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idproduct;
    @Column(nullable = false,unique = true)
    private String product_name;
    private int price;
    private int stock;
    private String description;
    private String manufacturer;
    private String category;
    private int condition_id;
    private String picture;


    public long getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(long id) {
        this.idproduct = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCondition_id() {
        return condition_id;
    }

    public void setCondition_id(int condition_id) {
        this.condition_id = condition_id;
    }

    public String getPicture() {
        return "/img/"+picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idproduct=" + idproduct +
                ", product_name='" + product_name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", description='" + description + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", category='" + category + '\'' +
                ", condition_id=" + condition_id +
                ", picture='" + picture + '\'' +
                '}';
    }
}
