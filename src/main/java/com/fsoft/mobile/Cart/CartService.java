package com.fsoft.mobile.Cart;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository repo;
    public int GetCartId(String username){
        List<Cart> listCart = (List<Cart>) repo.findAll();
        boolean checkAvailable=false;
        int cartId = 0;
        for(int i=0;i<listCart.size();i++){
            if(listCart.get(i).getUsername().toLowerCase().trim().equals(username.toLowerCase().trim())){
                checkAvailable=true;
                cartId=listCart.get(i).getIdcart();
                break;
            }
        }
        if(checkAvailable==false){
            Cart newcart = new Cart();
            newcart.setUsername(username);
            repo.save(newcart);
            cartId= newcart.getIdcart();
        }
        return cartId;
    }
}
