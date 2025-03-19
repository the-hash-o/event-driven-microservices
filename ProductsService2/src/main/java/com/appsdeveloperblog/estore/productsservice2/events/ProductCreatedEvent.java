package com.appsdeveloperblog.estore.productsservice2.events;

import lombok.Getter;

import java.math.BigDecimal;

public record ProductCreatedEvent(String productId, String title, BigDecimal price, int quantity) {

}
