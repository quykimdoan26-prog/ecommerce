package com.ecommerce.controller;

import com.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import com.ecommerce.entity.User;
import com.ecommerce.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String myOrders(Authentication auth, Model model) {
        User user = userService.findByUsername(auth.getName()).orElse(null);
        model.addAttribute("orders", user == null ? java.util.List.of()
                : orderService.getUserOrders(user.getId()));
        return "customer/orders";
    }

    @GetMapping("/{id}")
    public String viewOrder(@PathVariable Long id, Authentication auth, Model model) {
        User user = userService.findByUsername(auth.getName()).orElse(null);
        var order = orderService.getOrderById(id).filter(item -> user != null
                && item.getUser().getId().equals(user.getId()));
        order.ifPresent(item -> {
            model.addAttribute("order", item);
            model.addAttribute("items", orderService.getOrderItems(id));
        });
        return order.isPresent() ? "customer/order-detail" : "redirect:/orders";
    }

}
