package com.ecommerce.application.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ecommerce.application.dto.CartDTO;
import com.ecommerce.application.dto.ProductDTO;
import com.ecommerce.application.exception.InvalidDataException;
import com.ecommerce.application.exception.ResourceNotFoundException;
import com.ecommerce.application.model.Cart;
import com.ecommerce.application.model.Product;
import com.ecommerce.application.repository.CartRepository;


@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    private static final Logger logger = LoggerFactory.getLogger(CartService.class);


    // Convert Cart to CartDTO
    public CartDTO convertToDTO(Cart cart) {
        List<ProductDTO> productDTOs = cart.getProducts().stream()
                .map(product -> new ProductDTO(product.getId(), product.getDescription(), product.getAmount()))
                .collect(Collectors.toList());

        return new CartDTO(cart.getCartId(), cart.getLastUpdated(), productDTOs);
    }

    // Get cart info and convert to CartDTO
    public CartDTO getCartInfo(String cartId) {
        Cart cart = cartRepository.getCart(cartId);
        if (cart != null) {
            return convertToDTO(cart);
        }
        throw new ResourceNotFoundException("Carrito no encontrado con id: " + cartId);
    }

    // Generate a unique cart ID
    public String createCart() {
        String cartId = UUID.randomUUID().toString();
        Cart cart = new Cart(cartId, Instant.now().toEpochMilli());
        cartRepository.saveCart(cart);
        return cartId;
    }

    // Add products to cart
    public void addProductToCart(String cartId, Product product) {
        if (product == null || product.getAmount() <= 0) {
            throw new InvalidDataException("El producto no es válido. La cantidad debe ser mayor que 0.");
        }

        Cart cart = cartRepository.getCart(cartId);
        if (cart == null) {
            // Exception if Cart not found
            throw new ResourceNotFoundException("No se encontró el carrito con id: " + cartId);
        }

         // Check if product already exists
        boolean productExists = cart.getProducts().stream()
            .anyMatch(existingProduct -> existingProduct.getId() == product.getId());

        if (productExists) {
        throw new InvalidDataException("El producto con id " + product.getId() + " ya está en el carrito.");
        }

        cart.getProducts().add(product);
        cart.setLastUpdated(Instant.now().toEpochMilli()); // Update last activity timestamp
    
    }

    // Delete a cart manually
    public void deleteCart(String cartId) {
        cartRepository.deleteCart(cartId);
    }

    // Check for inactive carts and delete them (run this periodically)

    @Scheduled(fixedRate = 60000) // Every 1 minutes
    public void deleteInactiveCarts() {
        logger.info("Borrando carrritos inactivos...");
    long currentTime = Instant.now().toEpochMilli();
    cartRepository.getAllCarts().removeIf(cart -> currentTime - cart.getLastUpdated() > 600000); // 10 minutes
}



}

