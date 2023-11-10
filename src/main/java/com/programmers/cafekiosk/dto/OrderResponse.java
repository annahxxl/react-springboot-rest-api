package com.programmers.cafekiosk.dto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(Long id, Long totalPrice, LocalDateTime createdAt, List<OrderItemResponse> orderItems) {

    public static OrderResponse from(Long id, Long totalPrice, LocalDateTime createdAt, List<OrderItemResponse> orderItems) {
        return new OrderResponse(id, totalPrice, createdAt, orderItems);
    }
}