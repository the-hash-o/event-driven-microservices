package com.appsdeveloperblog.estore.productsservice.command

import com.appsdeveloperblog.estore.productsservice.events.ProductCreatedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import org.springframework.beans.BeanUtils
import java.math.BigDecimal

@Aggregate
class ProductAggregate{

    @AggregateIdentifier
    private lateinit var productId: String
    private lateinit var title: String
    private lateinit var price: BigDecimal
    private var quantity: Int = 0

    constructor()

    @CommandHandler
    constructor(createProductCommand: CreateProductCommand){
        if (createProductCommand.quantity <= 0) {
            throw IllegalArgumentException("Quantity must be greater than zero.")
        }

        if (createProductCommand.title.isBlank()) {
            throw IllegalArgumentException("Title must not be empty.")
        }

        val productCreatedEvent = ProductCreatedEvent(
            createProductCommand.productId,
            createProductCommand.title,
            createProductCommand.price,
            createProductCommand.quantity
        )

        BeanUtils.copyProperties(createProductCommand, productCreatedEvent)

        AggregateLifecycle.apply(productCreatedEvent)
    }

    @EventSourcingHandler
    fun on(productCreatedEvent: ProductCreatedEvent) {
        this.productId = productCreatedEvent.productId
        this.title = productCreatedEvent.title
        this.price = productCreatedEvent.price
        this.quantity = productCreatedEvent.quantity
    }
}
