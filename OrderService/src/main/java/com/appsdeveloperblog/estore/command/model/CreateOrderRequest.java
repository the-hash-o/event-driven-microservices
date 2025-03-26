package com.appsdeveloperblog.estore.command.model;

public record CreateOrderRequest(String productId, Integer quantity, String addressId) {
}
