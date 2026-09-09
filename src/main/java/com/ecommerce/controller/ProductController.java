package com.ecommerce.controller;

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

    // 1. Hiển thị tất cả sản phẩm
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAvailableProducts());
        model.addAttribute("categories", categoryService.getActiveCategories());
        return "customer/products";
    }

    // THÊM MỚI: 2. Hiển thị sản phẩm theo danh mục khi click từ menu dropdown
    @GetMapping("/category/{id}")
    public String listProductsByCategory(@PathVariable("id") Long categoryId, Model model) {
        // Gọi service lấy danh sách sản phẩm theo ID danh mục
        // (Đảm bảo trong ProductService của bạn có hàm này, nếu tên khác hãy sửa lại cho đúng)
        model.addAttribute("products", productService.getProductsByCategory(categoryId)); 
        
        // Vẫn phải truyền categories để giữ menu dropdown không bị trống
        model.addAttribute("categories", categoryService.getActiveCategories());
        
        // Truyền thêm thông tin danh mục đang xem (nếu bạn muốn hiển thị Tên danh mục ở trang HTML)
        categoryService.getCategoryById(categoryId).ifPresent(c -> model.addAttribute("currentCategory", c));
        
        return "customer/products"; // Vẫn dùng chung view products.html
    }

    // 3. Xem chi tiết 1 sản phẩm
    @GetMapping("/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        productService.getProductById(id).ifPresent(p -> model.addAttribute("product", p));
        
        // CẬP NHẬT: Truyền thêm categories để navbar ở trang chi tiết cũng hiển thị được dropdown
        model.addAttribute("categories", categoryService.getActiveCategories());
        
        return "customer/product-detail";
    }

    // 4. Thêm vào giỏ hàng
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