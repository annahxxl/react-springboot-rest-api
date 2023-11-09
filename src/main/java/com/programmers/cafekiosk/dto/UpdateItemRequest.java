package com.programmers.cafekiosk.dto;

import com.programmers.cafekiosk.entity.ItemType;

public record UpdateItemRequest(ItemType type, String name, Integer price, String description) {
}
