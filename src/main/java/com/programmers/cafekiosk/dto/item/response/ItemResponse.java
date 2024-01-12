package com.programmers.cafekiosk.dto.item.response;

import com.programmers.cafekiosk.entity.item.ItemStatus;
import com.programmers.cafekiosk.entity.item.ItemType;

import java.time.LocalDateTime;

public record ItemResponse(Long id, ItemType type, ItemStatus status, String name, Long price, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {

    public static ItemResponse from(Long id, ItemType type, ItemStatus status, String name, Long price, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new ItemResponse(id, type, status, name, price, description, createdAt, updatedAt);
    }
}
