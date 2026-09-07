package com.ecommerce.controller;

import com.ecommerce.entity.User;
import com.ecommerce.service.OrderService;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String checkout(Authentication auth, Model model) {
        User user = currentUser(auth);
        if (user == null) {
            return "redirect:/auth/login";
        }
        model.addAttribute("user", user);
        return "customer/checkout";
    }

    @PostMapping
    public String placeOrder(Authentication auth,
                             @RequestParam String shippingAddress,
                             @RequestParam(required = false) String notes,
                             @RequestParam(defaultValue = "COD") String paymentMethod) {
        User user = currentUser(auth);
        if (user == null) {
            return "redirect:/auth/login";
        }
        orderService.createFromCart(user, shippingAddress, notes, paymentMethod);
        return "redirect:/orders?success";
    }

    private User currentUser(Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) {
            return null;
        }
        return userService.findByUsername(auth.getName()).orElse(null);
    }
}
