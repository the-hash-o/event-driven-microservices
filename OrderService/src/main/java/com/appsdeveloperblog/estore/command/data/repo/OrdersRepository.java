package com.appsdeveloperblog.estore.command.data.repo;

import com.appsdeveloperblog.estore.command.data.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<OrderEntity, String> {
}
