package com.appsdeveloperblog.estore.productsservice2.command;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import java.math.BigDecimal;

@Getter
public class CreateProductCommand {

    @TargetAggregateIdentifier
    private final String productId;
    private final String title;
    private final BigDecimal price;
    private final int quantity;

    public CreateProductCommand(String productId, String title, BigDecimal price, int quantity) {
        this.productId = productId;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

}
