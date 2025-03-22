package com.appsdeveloperblog.estore.productsservice2.events;

import com.appsdeveloperblog.estore.productsservice2.data.entity.ProductEntity;
import com.appsdeveloperblog.estore.productsservice2.data.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
@RequiredArgsConstructor
public class ProductEventsHandler {

    private final ProductRepository productRepository;

    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        throw exception;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);
        try {
            productRepository.save(productEntity);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
