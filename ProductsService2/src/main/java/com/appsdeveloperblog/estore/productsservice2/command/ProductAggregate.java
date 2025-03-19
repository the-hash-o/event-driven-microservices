package com.appsdeveloperblog.estore.productsservice2.command;
import com.appsdeveloperblog.estore.productsservice2.events.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;
    private String title;
    private BigDecimal price;
    private int quantity;

    public ProductAggregate() {
        // Default constructor
    }

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

    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        this.productId = productCreatedEvent.productId();
        this.title = productCreatedEvent.title();
        this.price = productCreatedEvent.price();
        this.quantity = productCreatedEvent.quantity();
    }
}
