package com.programmers.cafekiosk.dto;

import com.programmers.cafekiosk.entity.ItemType;

public record OrderItemResponse(Long id, ItemType type, String name, Long price, Integer quantity) {

    public static OrderItemResponse from(Long id, ItemType type, String name, Long price, Integer quantity) {
        return new OrderItemResponse(id, type, name, price, quantity);
    }
}
