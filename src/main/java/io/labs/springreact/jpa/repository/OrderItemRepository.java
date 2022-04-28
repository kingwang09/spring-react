package io.labs.springreact.jpa.repository;

import io.labs.springreact.jpa.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
