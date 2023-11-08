package com.programmers.cafekiosk.repository;

import com.programmers.cafekiosk.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
