package com.appsdeveloperblog.estore.productsservice2.command;

import com.appsdeveloperblog.estore.estorecore.command.ProductReservedCommand;
import com.appsdeveloperblog.estore.estorecore.event.ProductReservedEvent;
import com.appsdeveloperblog.estore.productsservice2.events.ProductCreatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
@NoArgsConstructor
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;
    private String title;
    private BigDecimal price;
    private int quantity;

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {
        if (createProductCommand.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        if (createProductCommand.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title must not be empty.");
        }

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(
                createProductCommand.getProductId(),
                createProductCommand.getTitle(),
                createProductCommand.getPrice(),
                createProductCommand.getQuantity()
        );

        BeanUtils.copyProperties(createProductCommand, productCreatedEvent);

        AggregateLifecycle.apply(productCreatedEvent);
    }

    @CommandHandler
    public void on(ProductReservedCommand reserveProductCommand) {
        if (reserveProductCommand.getQuantity() > quantity) {
            throw new IllegalArgumentException("Insufficient number of items in stock.");
        }

        ProductReservedEvent productReservedEvent = ProductReservedEvent.builder()
                .orderId(reserveProductCommand.getOrderId())
                .productId(reserveProductCommand.getProductId())
                .quantity(reserveProductCommand.getQuantity())
                .userId(reserveProductCommand.getUserId())
                .build();

        AggregateLifecycle.apply(productReservedEvent);
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        this.productId = productCreatedEvent.getProductId();
        this.title = productCreatedEvent.getTitle();
        this.price = productCreatedEvent.getPrice();
        this.quantity = productCreatedEvent.getQuantity();
    }

    @EventSourcingHandler
    public void on(ProductReservedEvent reserveProductEvent) {
        this.quantity -= reserveProductEvent.getQuantity();
    }
}
