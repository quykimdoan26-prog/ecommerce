package com.ecommerce.controller;

import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.CartService;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private CartService cartService;
    
    @Autowired
    private UserService userService;

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAvailableProducts());
        model.addAttribute("categories", categoryService.getActiveCategories());
        return "customer/products";
    }

    @GetMapping("/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        productService.getProductById(id).ifPresent(p -> model.addAttribute("product", p));
        return "customer/product-detail";
    }

    @PostMapping("/add-to-cart")
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
        
        return "redirect:/products/" + productId + "?added=success";
    }
}
