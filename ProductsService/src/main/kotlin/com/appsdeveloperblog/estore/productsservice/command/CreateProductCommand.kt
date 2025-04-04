package com.appsdeveloperblog.estore.productsservice.command

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.math.BigDecimal
import java.util.UUID

data class CreateProductCommand (
    @TargetAggregateIdentifier
    val productId: String,
    val title: String,
    val price: BigDecimal,
    val quantity: Int
)