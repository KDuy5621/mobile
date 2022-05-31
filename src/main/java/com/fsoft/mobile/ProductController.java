package com.fsoft.mobile;

import com.fsoft.mobile.Product.Product;
import com.fsoft.mobile.Product.ProductNotFoundException;
import com.fsoft.mobile.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/products/add")
    public String showFormProduct(Model model, HttpSession session, RedirectAttributes ra) {
        int type =0;
        if(session.getAttribute("type")!=null){

            type =(int) session.getAttribute("type");
        }
        if (type == 1) {
            String usert = "admin";
            model.addAttribute("product", new Product());
            model.addAttribute("usert", usert);
            return "addproducts";
        } else {
            ra.addFlashAttribute("mess", "This is Admin Page");
            return "redirect:/products/show";
        }


    }
    @PostMapping("/products/add")
    public String addNewProduct(Model model,Product product){
        service.save(product);
        String usert = "admin";
        model.addAttribute("product", new Product());
        model.addAttribute("usert", usert);
        return "addproducts";

    }

    @GetMapping("/products/show")
    public String showProduct(Model model, HttpSession session) {
        int type =0;
        if(session.getAttribute("type")!=null){

            type =(int) session.getAttribute("type");
        }

        if (type == 1) {
            String usert = "admin";
            model.addAttribute("usert", usert);
            return "addproducts";
        } else {
            if (session.getAttribute("username") != null) {
                String usert = session.getAttribute("username").toString();
                model.addAttribute("usert", usert);
            }
            List<Product> listProducts = service.listAll();
            model.addAttribute("listProducts", listProducts);
            return "showproducts";
        }

    }

    @GetMapping("/products/detail/{id}")
    public String showDetails(@PathVariable("id") Long id, Model model, HttpSession session) {
        try {
            int type =0;
            if(session.getAttribute("type")!=null){

                type =(int) session.getAttribute("type");
            }
            if (type == 1) {
                String usert = "admin";
                model.addAttribute("usert", usert);
                return "addproducts";
            } else {
                Product product = service.get(id);
                model.addAttribute("products", product);
                return "detailproduct";
            }
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
        }
        return "login";
    }
}

