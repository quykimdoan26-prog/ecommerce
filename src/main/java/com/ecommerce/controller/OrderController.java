package com.ecommerce.controller;

import com.ecommerce.entity.Order;
import com.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public String myOrders(Authentication auth, Model model) {
        // TODO: get current user and fetch their orders
        return "customer/orders";
    }

    @GetMapping("/{id}")
    public String viewOrder(@PathVariable Long id, Model model) {
        orderService.getOrderById(id).ifPresent(order -> {
            model.addAttribute("order", order);
            model.addAttribute("items", orderService.getOrderItems(id));
        });
        return "customer/order-detail";
    }

    @PostMapping("/create")
    public String createOrder(@ModelAttribute Order order) {
        Order saved = orderService.createOrder(order);
        return "redirect:/orders/" + saved.getId();
    }
}
