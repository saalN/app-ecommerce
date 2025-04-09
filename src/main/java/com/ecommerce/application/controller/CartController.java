package com.ecommerce.application.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.application.dto.CartDTO;
import com.ecommerce.application.model.Product;
import com.ecommerce.application.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

   
    private final CartService cartService;
    

     // Get a cart unsing the DTO
    @GetMapping("/{cartId}")
    public CartDTO getCart(@PathVariable String cartId) {
        return cartService.getCartInfo(cartId);
    }

    // Create a cart
    @PostMapping
    public ResponseEntity<String> createCart() {
        String cartId = cartService.createCart();
        return ResponseEntity.ok(cartId);
    }

    // Add a product to a cart
    @PostMapping("/{cartId}/product")
    public ResponseEntity<Void> addProductToCart(@PathVariable String cartId, @RequestBody Product product) {
        cartService.addProductToCart(cartId, product);
        return ResponseEntity.ok().build();
    }


    // Delete a cart manually
    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable String cartId) {
        cartService.deleteCart(cartId);
        return ResponseEntity.noContent().build();
    }
}
