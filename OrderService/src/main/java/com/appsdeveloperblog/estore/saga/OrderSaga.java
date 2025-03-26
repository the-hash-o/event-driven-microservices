package com.appsdeveloperblog.estore.saga;

import com.appsdeveloperblog.estore.command.model.event.OrderCreatedEvent;
import com.appsdeveloperblog.estore.estorecore.command.ProductReservedCommand;
import com.appsdeveloperblog.estore.estorecore.event.ProductReservedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
public class OrderSaga {

    @Autowired
    private transient CommandGateway commandGateway;
    private final Logger log = LoggerFactory.getLogger(OrderSaga.class);

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent orderCreatedEvent) {
        ProductReservedCommand reserveProductCommand = ProductReservedCommand.builder()
                .orderId(orderCreatedEvent.getOrderId())
                .productId(orderCreatedEvent.getProductId())
                .quantity(orderCreatedEvent.getQuantity())
                .userId(orderCreatedEvent.getUserId())
                .build();

        log.info("OrderCreatedEvent handled for event: {}", orderCreatedEvent);

        commandGateway.send(reserveProductCommand, (commandMessage, commandResultMessage) -> {
            if (commandResultMessage.isExceptional()) {
                log.error("Exceptional reserve product command {}", commandResultMessage.getPayload());
            }
        });
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(ProductReservedEvent productReserveEvent) {
        log.info("ProductReservedEvent handled for event: {}", productReserveEvent);
    }
}
