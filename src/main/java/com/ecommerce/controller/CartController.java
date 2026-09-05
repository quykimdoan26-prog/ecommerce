package com.ecommerce.controller;

import com.ecommerce.entity.User;
import com.ecommerce.service.CartService;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    
    @Autowired
    private UserService userService;

    @GetMapping
    public String viewCart(Authentication auth, Model model) {
        if (auth == null || !auth.isAuthenticated()) {
            return "redirect:/auth/login";
        }
        
        User user = userService.findByUsername(auth.getName()).orElse(null);
        if (user == null) {
            return "redirect:/auth/login";
        }

        model.addAttribute("items", cartService.getCartItems(user.getId()));
        model.addAttribute("total", cartService.getCartTotal(user.getId()));
        model.addAttribute("user", user);
        return "customer/cart";
    }

    @PostMapping("/add")
    public String addToCart(Authentication auth, 
                           @RequestParam Long productId,
                           @RequestParam(defaultValue = "1") Integer quantity) {
        if (auth == null || !auth.isAuthenticated()) {
            return "redirect:/auth/login";
        }
        
        User user = userService.findByUsername(auth.getName()).orElse(null);
        if (user != null) {
            cartService.addToCart(user, productId, quantity);
        }
        
        return "redirect:/products/" + productId + "?added=true";
    }

    @PostMapping("/update/{id}")
    public String updateCart(@PathVariable Long id,
                            @RequestParam Integer quantity) {
        cartService.updateCartItem(id, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id) {
        cartService.removeFromCart(id);
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart(Authentication auth) {
        User user = userService.findByUsername(auth.getName()).orElse(null);
        if (user != null) {
            cartService.clearCart(user.getId());
        }
        return "redirect:/cart";
    }
}
