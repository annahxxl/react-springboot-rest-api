package com.programmers.cafekiosk.repository;

import com.programmers.cafekiosk.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
