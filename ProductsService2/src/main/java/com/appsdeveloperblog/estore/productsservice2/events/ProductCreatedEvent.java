package com.appsdeveloperblog.estore.productsservice2.events;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
public class ProductCreatedEvent{
    private String productId;
    private String title;
    private BigDecimal price;
    private int quantity;
}
