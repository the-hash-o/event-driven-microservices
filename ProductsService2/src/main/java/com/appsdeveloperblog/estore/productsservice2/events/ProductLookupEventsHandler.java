package com.appsdeveloperblog.estore.productsservice2.events;


import com.appsdeveloperblog.estore.productsservice2.data.entity.ProductLookupEntity;
import com.appsdeveloperblog.estore.productsservice2.data.repo.ProductLookupRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
@RequiredArgsConstructor
public class ProductLookupEventsHandler {

    private final ProductLookupRepository productLookupRepository;

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        ProductLookupEntity productLookupEntity = new ProductLookupEntity(productCreatedEvent.getProductId(), productCreatedEvent.getTitle());

        //if this fails and it throws error the other handler will not know about this and will continue successfully
        //14.Introduction slide
        productLookupRepository.save(productLookupEntity);
    }
}
