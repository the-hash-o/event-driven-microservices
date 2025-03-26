package com.appsdeveloperblog.estore.productsservice2.query;

import com.appsdeveloperblog.estore.productsservice2.data.repo.ProductRepository;
import com.appsdeveloperblog.estore.productsservice2.model.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductsQueryHandler {

    private final ProductRepository productRepository;

    @QueryHandler
    public List<ProductRequest> findProducts(FindProductsQuery query){
        ArrayList<ProductRequest> productRequests = new ArrayList<>();

        productRepository.findAll().forEach(product -> {
            ProductRequest productRequest = new ProductRequest();
            BeanUtils.copyProperties(product, productRequest);
            productRequests.add(productRequest);
        });

        return productRequests;
    }
}
