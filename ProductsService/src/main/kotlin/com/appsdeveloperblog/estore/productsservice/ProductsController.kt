package com.appsdeveloperblog.estore.productsservice

import com.appsdeveloperblog.estore.productsservice.command.CreateProductCommand
import com.appsdeveloperblog.estore.productsservice.model.CreateProductRestModel
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/products")
class ProductsController(private val env: Environment, private val commandGateway: CommandGateway) {

    @PostMapping
    fun handlePost(@RequestBody product: CreateProductRestModel): String {
        val createProductCommand = CreateProductCommand(
            productId = UUID.randomUUID(),
            title = product.title,
            price = product.price,
            quantity = product.quantity
        )

        val commandGatewayResponse = try {
            commandGateway.sendAndWait(createProductCommand)
        } catch (e: Exception) {
            e.localizedMessage
        }

        return commandGatewayResponse
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