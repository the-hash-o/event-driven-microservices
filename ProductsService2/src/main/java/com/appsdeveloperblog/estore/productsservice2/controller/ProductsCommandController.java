package com.appsdeveloperblog.estore.productsservice2.controller;

import com.appsdeveloperblog.estore.productsservice2.command.CreateProductCommand;
import com.appsdeveloperblog.estore.productsservice2.model.CreateProductRestModel;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductsCommandController {

    private final Environment env;
    private final CommandGateway commandGateway;

    @Autowired
    public ProductsCommandController(Environment env, CommandGateway commandGateway) {
        this.env = env;
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String handlePost(@Valid @RequestBody CreateProductRestModel product) {
        CreateProductCommand createProductCommand = new CreateProductCommand(
                UUID.randomUUID().toString(),
                product.getTitle(),
                product.getPrice(),
                product.getQuantity()
        );

        String commandGatewayResponse;
        try {
            commandGatewayResponse = commandGateway.sendAndWait(createProductCommand);
        } catch (Exception e) {
            commandGatewayResponse = e.getLocalizedMessage();
        }

        return commandGatewayResponse;
    }

//    @GetMapping
//    public String handleGet() {
//        return "HTTP GET Handled by instance with port " + env.getProperty("local.server.port");
//    }
//
//    @DeleteMapping
//    public String handleDelete() {
//        return "HTTP DELETE Handled";
//    }
//
//    @PutMapping
//    public String handlePut() {
//        return "HTTP PUT Handled";
//    }
}
