package com.appsdeveloperblog.estore.command.data.entity;

import com.appsdeveloperblog.estore.command.model.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

@Table(name = "orders")
@Entity
@Data
public class OrderEntity {
    @Id
    @Column(unique = true)
    public String orderId;
    private String productId;
    private String userId;
    private int quantity;
    private String addressId;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
