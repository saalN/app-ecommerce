package com.ecommerce.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDTO {

    private int id; // ID del producto
    private String description; // Descripción del producto
    private double amount; // Precio del producto


}
