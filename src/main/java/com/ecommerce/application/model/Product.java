package com.ecommerce.application.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;


@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
public class Product {

    private int id;
    private String description;
    private double amount;

}


