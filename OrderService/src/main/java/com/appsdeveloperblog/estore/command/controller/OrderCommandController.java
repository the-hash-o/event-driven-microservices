package com.appsdeveloperblog.estore.command.controller;

import com.appsdeveloperblog.estore.command.model.CreateOrderCommand;
import com.appsdeveloperblog.estore.command.model.CreateOrderRequest;
import com.appsdeveloperblog.estore.command.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderCommandController {

    private final CommandGateway commandGateway;
    private final OrderService orderService;

    @PostMapping
    private String createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        CreateOrderCommand createOrderCommand = orderService.createOrder(createOrderRequest);
        String response = commandGateway.sendAndWait(createOrderCommand);
        return response;
    }
}
