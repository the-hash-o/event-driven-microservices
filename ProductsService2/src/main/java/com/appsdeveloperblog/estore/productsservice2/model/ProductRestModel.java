package com.appsdeveloperblog.estore.productsservice2.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRestModel {
    private String productId;
    private String title;
    private BigDecimal price;
    private int quantity;
}
