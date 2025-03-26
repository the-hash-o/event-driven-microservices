package com.appsdeveloperblog.estore.command.service;

import com.appsdeveloperblog.estore.command.model.CreateOrderCommand;
import com.appsdeveloperblog.estore.command.model.CreateOrderRequest;
import com.appsdeveloperblog.estore.command.model.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    private final static String USER_ID = "27b95829-4f3f-4ddf-8983-151ba010e35b";

    public CreateOrderCommand createOrder(CreateOrderRequest createOrderRequest) {
        return new CreateOrderCommand(UUID.randomUUID().toString(), USER_ID, createOrderRequest.productId(), createOrderRequest.quantity(), createOrderRequest.addressId(), OrderStatus.CREATED);
    }
}
