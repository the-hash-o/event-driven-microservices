package com.appsdeveloperblog.estore.productsservice

import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductsController(private val env: Environment) {

    @PostMapping
    fun handlePost(): String {
        return "HTTP POST Handled"
    }

    @GetMapping
    fun handleGet(): String {
        return "HTTP GET Handled by instance with port ${env.getProperty("local.server.port")}"
    }

    @DeleteMapping
    fun handleDelete(): String {
        return "HTTP DELETE Handled"
    }

    @PutMapping
    fun handlePut(): String {
        return "HTTP PUT Handled"
    }
}