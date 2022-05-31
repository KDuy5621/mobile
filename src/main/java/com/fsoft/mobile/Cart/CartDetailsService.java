package com.fsoft.mobile.Cart;

import com.fsoft.mobile.Product.Product;
import com.fsoft.mobile.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartDetailsService {
    @Autowired
    private CartDetailsRepository cartdetailsrepo;

    @Autowired
    private ProductRepository productrepo;

    public boolean addToCartDetails(long productid,int cartid){
        List<CartDetails> listCartDetails = (List<CartDetails>) cartdetailsrepo.findAll();
        List<CartDetails> usercartdetails=new ArrayList<>();
        Product p = productrepo.findById(productid).get();
        boolean checkAvailable=false;


        for(int i=0;i<listCartDetails.size();i++){
            if(listCartDetails.get(i).getIdcart()==cartid){
                usercartdetails.add(listCartDetails.get(i));
            }
        }


        if(usercartdetails.size()==0&&p.getStock()!=0){
            CartDetails cartDetails=new CartDetails();
            cartDetails.setIdproduct(productid);
            cartDetails.setIdcart(cartid);
            cartDetails.setQuantity(1);
            productrepo.save(p);
            cartdetailsrepo.save(cartDetails);

        }else {
            for(int i=0;i<usercartdetails.size();i++){
                if(usercartdetails.get(i).getIdproduct()==productid&&p.getStock()!=0){
                    usercartdetails.get(i).setQuantity(usercartdetails.get(i).getQuantity()+1);
                    cartdetailsrepo.save( usercartdetails.get(i));
                    productrepo.save(p);
                    checkAvailable=true;
                    break;
                }
            }
            if(checkAvailable==false&&p.getStock()!=0){
                CartDetails cartDetails=new CartDetails();
                cartDetails.setIdproduct(productid);
                cartDetails.setIdcart(cartid);
                cartDetails.setQuantity(1);
                cartdetailsrepo.save(cartDetails);
                productrepo.save(p);
                checkAvailable=true;
            }
        }
        return checkAvailable;
    }

    public List<CartDetails> getCartdetail(int cartid){
        List<CartDetails> cartDetails = new ArrayList<>();
        List<CartDetails> getAllCartDetails= (List<CartDetails>) cartdetailsrepo.findAll();
        for(int i=0;i<getAllCartDetails.size();i++){
            if(getAllCartDetails.get(i).getIdcart()==cartid){
                cartDetails.add(getAllCartDetails.get(i));
            }
        }
        return cartDetails;
    }

    public void deleteProductCart(int cartdetailsid){
        cartdetailsrepo.deleteById(cartdetailsid);

    }
    public void clearCart(int cartid){
        List<CartDetails> listCartDetails = (List<CartDetails>) cartdetailsrepo.findAll();
        for(int i=0;i<listCartDetails.size();i++){
            if(listCartDetails.get(i).getIdcart()==cartid){
                cartdetailsrepo.deleteById(listCartDetails.get(i).getIdcartdetail());
            }
        }
    }
}
