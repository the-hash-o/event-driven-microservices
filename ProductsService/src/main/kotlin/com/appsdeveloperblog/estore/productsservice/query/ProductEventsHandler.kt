package com.appsdeveloperblog.estore.productsservice.query

import com.appsdeveloperblog.estore.productsservice.data.ProductEntity
import com.appsdeveloperblog.estore.productsservice.data.repo.ProductRepository
import com.appsdeveloperblog.estore.productsservice.events.ProductCreatedEvent
import org.axonframework.eventhandling.EventHandler
import org.springframework.beans.BeanUtils
import org.springframework.stereotype.Component

@Component
class ProductEventsHandler(private val productRepository: ProductRepository) {

    @EventHandler
    fun on(productCreatedEvent: ProductCreatedEvent) {
        val productEntity = ProductEntity()
        BeanUtils.copyProperties(productCreatedEvent, productEntity)
        productRepository.save(productEntity)
    }
}