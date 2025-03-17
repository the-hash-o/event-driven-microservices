package com.appsdeveloperblog.estore.productsservice.query

import com.appsdeveloperblog.estore.productsservice.data.ProductEntity
import com.appsdeveloperblog.estore.productsservice.data.repo.ProductRepository
import com.appsdeveloperblog.estore.productsservice.events.ProductCreatedEvent
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
class ProductEventsHandler(private val productRepository: ProductRepository) {

    @EventHandler
    fun on(productCreatedEvent: ProductCreatedEvent) {
        val productEntity = ProductEntity(
            productId = productCreatedEvent.productId,
            title = productCreatedEvent.title,
            price = productCreatedEvent.price,
            quantity = productCreatedEvent.quantity
        )
        try {
            val productList = productRepository.findByProductId(productEntity.productId)
            if (productList.isEmpty()) {
                productRepository.save(productEntity)
            }
        } catch (e: Exception) {
            println(e.message)
        }
    }
}