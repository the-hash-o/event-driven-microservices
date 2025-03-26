package com.appsdeveloperblog.estore.productsservice2.controller;

import com.appsdeveloperblog.estore.productsservice2.model.ProductRequest;
import com.appsdeveloperblog.estore.productsservice2.query.FindProductsQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(("/products"))
@RequiredArgsConstructor
public class ProductsQueryController {

    private final QueryGateway queryGateway;

    @GetMapping
    private List<ProductRequest> getProducts() {

        FindProductsQuery findProductsQuery = new FindProductsQuery();

        return queryGateway.query(findProductsQuery, ResponseTypes.multipleInstancesOf(ProductRequest.class)).join();
    }
}
