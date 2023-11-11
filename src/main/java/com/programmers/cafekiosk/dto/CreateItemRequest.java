package com.programmers.cafekiosk.dto;

import com.programmers.cafekiosk.entity.ItemType;

public record CreateItemRequest(ItemType type, String name, Long price, String description) {
}
