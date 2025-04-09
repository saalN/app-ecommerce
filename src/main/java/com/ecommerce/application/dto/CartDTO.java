package com.ecommerce.application.dto;

import java.util.ArrayList;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class CartDTO {

    private String cartId; 
    private long lastUpdated;
    private List<ProductDTO> products = new ArrayList<>();

    public CartDTO(String cartId, List<ProductDTO> products) {
        this.cartId = cartId;
        this.products = products;
    }

    public CartDTO(String cartId, long lastUpdated) {
        this.cartId = cartId;
        this.lastUpdated = lastUpdated;
        this.products = new ArrayList<>();
    }
   
}
