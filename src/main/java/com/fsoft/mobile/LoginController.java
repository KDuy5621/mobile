package com.fsoft.mobile;

import com.fsoft.mobile.Cart.CartService;
import com.fsoft.mobile.User.User;
import com.fsoft.mobile.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
public class LoginController {
    @Autowired
    private UserService userservice;
    @Autowired
    private CartService cartservice;


    @GetMapping("")
    public String showHomePage(Model model, HttpSession session, RedirectAttributes ra) {
        if (session.getAttribute("username") == null) {
            model.addAttribute("users", new User());
            return "login";
        } else {
            int type =0;
            if(session.getAttribute("type")!=null){

                type =(int) session.getAttribute("type");
            }
            if (type == 1) {
                String usert = "admin";
                model.addAttribute("usert", usert);
                return "redirect:/products/add";
            } else {
                ra.addFlashAttribute("mess", "You are already logged in!");
                return "redirect:/products/show";
            }

        }

    }

    @PostMapping("/login")
    public String login(User user, RedirectAttributes ra, HttpSession session, Model model) {
        if (userservice.login(user.getUsername(), user.getPassword())) {
            int cartid = cartservice.GetCartId(user.getUsername());
            int type = userservice.getTypeofuser(user.getUsername());
            String usern = user.getUsername().toLowerCase().trim();
            session.setAttribute("username", usern);
            session.setAttribute("cartid", cartid);
            session.setAttribute("type", type);
            if (type == 1) {
                String usert = "admin";
                model.addAttribute("usert", usert);
                return "redirect:/products/add";
            } else {
                ra.addFlashAttribute("mess", "Welcome to MobileStore");
                return "redirect:/products/show";
            }

        } else {
            ra.addFlashAttribute("messlogout", "Username or Password wrong, please try again!!!");
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes ra) {
        session.removeAttribute("username");
        session.removeAttribute("cartid");
        session.removeAttribute("type");
        ra.addFlashAttribute("messlogout", "Logout Successfully!!!");
        return "redirect:/";
    }

    @GetMapping("/login")
    public String showLogin(){
        return "redirect:/";
    }
}


