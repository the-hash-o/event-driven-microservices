package com.appsdeveloperblog.estore.productsservice2.events;

import com.appsdeveloperblog.estore.estorecore.event.ProductReservedEvent;
import com.appsdeveloperblog.estore.productsservice2.data.entity.ProductEntity;
import com.appsdeveloperblog.estore.productsservice2.data.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
@RequiredArgsConstructor
public class ProductEventsHandler {

    private final ProductRepository productRepository;
    private final Logger log = LoggerFactory.getLogger(ProductEventsHandler.class);

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

    @EventHandler
    public void on(ProductReservedEvent event) {
        ProductEntity productEntity = productRepository.findByProductId(event.getProductId());
        productEntity.setQuantity(productEntity.getQuantity() - event.getQuantity());
        productRepository.save(productEntity);

        log.info("Product reserved event: {}", event.getProductId());
    }
}
