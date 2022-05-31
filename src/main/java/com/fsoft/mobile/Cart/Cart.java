package com.fsoft.mobile.Cart;

import javax.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcart;
    @Column(length = 15,nullable = false)
    private String username;

    public int getIdcart() {
        return idcart;
    }

    public void setIdcart(int idcar) {
        this.idcart = idcar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "idcar=" + idcart +
                ", username='" + username + '\'' +
                '}';
    }

}
