package com.appsdeveloperblog.estore.command.model.event;

import com.appsdeveloperblog.estore.command.model.OrderStatus;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class OrderCreatedEvent {

    @TargetAggregateIdentifier
    private final String orderId;
    private final String userId;
    private final String productId;
    private final int quantity;
    private final String addressId;
    private final OrderStatus orderStatus;
}