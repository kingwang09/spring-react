package io.labs.springreact.jpa.repository;

import io.labs.springreact.jpa.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
