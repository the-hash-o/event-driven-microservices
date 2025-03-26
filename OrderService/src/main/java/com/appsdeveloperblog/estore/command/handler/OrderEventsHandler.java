package com.appsdeveloperblog.estore.command.handler;

import com.appsdeveloperblog.estore.command.data.entity.OrderEntity;
import com.appsdeveloperblog.estore.command.data.repo.OrdersRepository;
import com.appsdeveloperblog.estore.command.model.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderEventsHandler {

    private final OrdersRepository ordersRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderEventsHandler(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
        this.modelMapper = new ModelMapper();
    }

    @EventHandler
    public void on(OrderCreatedEvent orderCreatedEvent) {
        OrderEntity orderEntity = modelMapper.map(orderCreatedEvent, OrderEntity.class);
        try {
            ordersRepository.save(orderEntity);
            log.info("Order created event: {}", orderCreatedEvent);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
