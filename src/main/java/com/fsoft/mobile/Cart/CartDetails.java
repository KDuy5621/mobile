package com.fsoft.mobile.Cart;

import javax.persistence.*;

@Entity
@Table(name = "cartdetails")
public class CartDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcartdetail;
    private int idcart;
    private long idproduct;

    private int quantity;

    public int getIdcartdetail() {
        return idcartdetail;
    }

    public void setIdcartdetail(int idcartdetail) {
        this.idcartdetail = idcartdetail;
    }

    public int getIdcart() {
        return idcart;
    }

    public void setIdcart(int idcart) {
        this.idcart = idcart;
    }

    public long getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(long idproduct) {
        this.idproduct = idproduct;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartDetails{" +
                "idcartdetail=" + idcartdetail +
                ", idcart=" + idcart +
                ", idproduct=" + idproduct +
                ", quantity=" + quantity +
                '}';
    }

}
