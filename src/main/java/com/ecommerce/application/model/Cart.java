package com.ecommerce.application.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;


@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
public class Cart {
  
    private String cartId; // Unique identifier for the cart
    private List<Product> products = new ArrayList<>();
    private long lastUpdated; // Timestamp to track inactivity

    public Cart(String cartId, long lastUpdated) {
        this.cartId = cartId;
        this.lastUpdated = lastUpdated;
        this.products = new ArrayList<>();
    }
    
}
