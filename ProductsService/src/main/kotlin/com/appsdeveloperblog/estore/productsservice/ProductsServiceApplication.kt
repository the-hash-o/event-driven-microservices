package com.appsdeveloperblog.estore.productsservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class ProductsServiceApplication

fun main(args: Array<String>) {
    runApplication<ProductsServiceApplication>(*args)
}
