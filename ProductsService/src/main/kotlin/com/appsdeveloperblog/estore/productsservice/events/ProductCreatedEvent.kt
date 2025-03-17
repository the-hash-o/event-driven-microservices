package com.appsdeveloperblog.estore.productsservice.events

import java.math.BigDecimal
import java.util.*

data class ProductCreatedEvent(val productId: UUID,
                               val title: String,
                               val price: BigDecimal,
                               val quantity: Int)