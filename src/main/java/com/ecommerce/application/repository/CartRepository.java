package com.ecommerce.application.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


import com.ecommerce.application.model.Cart;
import com.ecommerce.application.service.CartService;

@Repository
public class CartRepository {
    private final Map<String, Cart> carts = new HashMap<>();

     private static final Logger logger = LoggerFactory.getLogger(CartService.class);
    
    // Save a cart
    public void saveCart(Cart cart) {
        carts.put(cart.getCartId(), cart);
        logger.info("Guardando carrito con id: " + cart.getCartId());
    }
    
    // Get a cart by ID
    public Cart getCart(String cartId) {
        printAllCarts();
        return carts.get(cartId);
    }
    
    // Delete a cart
    public void deleteCart(String cartId) {
        carts.remove(cartId);
    }
    
    // List all carts (for debugging, optional)
    public Collection<Cart> getAllCarts() {
        return carts.values();
    }

    public void printAllCarts() {
        Collection<Cart> allCarts = getAllCarts();
        for (Cart cart : allCarts) {
            logger.info("Cart ID: " + cart.getCartId());
            logger.info("Last Updated: " + cart.getLastUpdated());
            logger.info("Products: " + cart.getProducts()); 
        }
        
    }
}
