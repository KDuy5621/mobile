package com.fsoft.mobile;

import com.fsoft.mobile.Cart.CartDetails;
import com.fsoft.mobile.Cart.CartDetailsService;
import com.fsoft.mobile.Product.Product;
import com.fsoft.mobile.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartDetailsController {
    @Autowired
    private CartDetailsService cartdetailservice;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products/addtocart/{id}")
    public String addToCart(@PathVariable("id") Long id, HttpSession session, RedirectAttributes ra) {

        int cartid = (int) session.getAttribute("cartid");
        if (cartdetailservice.addToCartDetails(id, cartid)) {
            ra.addFlashAttribute("mess", "successfully added the product to your cart!");
        }
        return "redirect:/cart";

    }

    @GetMapping("/cart")
    public String showCart(HttpSession session, Model model) {

        int type=0;
        if(session.getAttribute("type")!=null){

            type=(int) session.getAttribute("type");
        }
        if (type == 1) {
            String usert = "admin";
            model.addAttribute("usert", usert);
            return "redirect:/";
        } else if (type == 0) {
            return "redirect:/";
        } else {
            int cartid = (int) session.getAttribute("cartid");
            List<CartDetails> getCartdetails = cartdetailservice.getCartdetail(cartid);
            model.addAttribute("getCartdetails", getCartdetails);
            List<Product> getProduct = new ArrayList<>();
            for (int i = 0; i < getCartdetails.size(); i++) {
                Product p = productRepository.findById(getCartdetails.get(i).getIdproduct()).get();
                getProduct.add(p);
            }
            model.addAttribute("getProduct", getProduct);
            return "cart";
        }

    }
    @GetMapping("/cart/delete/{id}")
    public String delete(@PathVariable("id") int id, RedirectAttributes ra,HttpSession session,Model model) {
        int type =0;
        if(session.getAttribute("type")!=null){

            type=(int) session.getAttribute("type");
        }
        if (type == 1) {
            String usert = "admin";
            model.addAttribute("usert", usert);
            return "redirect:/";
        } else if (type == 0) {
            return "redirect:/";
        } else {

        cartdetailservice.deleteProductCart(id);
        ra.addFlashAttribute("mess", "Delete Successfully!!!");
        return "redirect:/cart";}
    }

    @GetMapping("/cart/clear")
    public String clear(HttpSession session, RedirectAttributes ra,Model model) {
        int type =0;
        if(session.getAttribute("type")!=null){
            type=(int) session.getAttribute("type");
        }
        if (type == 1) {
            String usert = "admin";
            model.addAttribute("usert", usert);
            return "redirect:/";
        } else if (type == 0) {
            return "redirect:/";
        } else {
        int cartid = (int) session.getAttribute("cartid");
        cartdetailservice.clearCart(cartid);
        ra.addFlashAttribute("mess", "Clear Successfully!!!");
        return "redirect:/cart";}
    }

    @GetMapping("products/show/product/addtocart/{id}")
    public String addToCart2(@PathVariable("id") Long id,HttpSession session,Model model) {
        int type =0;
        if(session.getAttribute("type")!=null){

            type=(int) session.getAttribute("type");
        }
        if (type == 1) {
            String usert = "admin";
            model.addAttribute("usert", usert);
            return "redirect:/";
        } else if (type == 0) {
            return "redirect:/";
        } else {
        return "redirect:/products/addtocart/" + id;}
    }
}
