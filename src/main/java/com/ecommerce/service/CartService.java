package com.ecommerce.service;

import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.repository.CartItemRepository;
import com.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private ProductRepository productRepository;

    public CartItem addToCart(User user, Long productId, Integer quantity) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            return null;
        }

        Optional<CartItem> existingItem = cartItemRepository.findByUserIdAndProductId(user.getId(), productId);
        CartItem cartItem;

        if (existingItem.isPresent()) {
            cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product.get());
            cartItem.setQuantity(quantity);
            cartItem.setUnitPrice(product.get().getPrice());
        }

        cartItem.setUpdatedAt(System.currentTimeMillis());
        return cartItemRepository.save(cartItem);
    }

    public List<CartItem> getCartItems(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }

    public CartItem updateCartItem(Long cartItemId, Integer quantity) {
        Optional<CartItem> item = cartItemRepository.findById(cartItemId);
        if (item.isPresent()) {
            CartItem cartItem = item.get();
            if (quantity <= 0) {
                cartItemRepository.deleteById(cartItemId);
                return null;
            }
            cartItem.setQuantity(quantity);
            cartItem.setUpdatedAt(System.currentTimeMillis());
            return cartItemRepository.save(cartItem);
        }
        return null;
    }

    public void removeFromCart(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    public void clearCart(Long userId) {
        cartItemRepository.deleteByUserId(userId);
    }

    public Double getCartTotal(Long userId) {
        List<CartItem> items = getCartItems(userId);
        return items.stream()
            .mapToDouble(item -> item.getUnitPrice() * item.getQuantity())
            .sum();
    }
}
