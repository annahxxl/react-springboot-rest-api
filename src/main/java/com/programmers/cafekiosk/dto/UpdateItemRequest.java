package com.programmers.cafekiosk.dto;

import com.programmers.cafekiosk.entity.ItemStatus;
import com.programmers.cafekiosk.entity.ItemType;

public record UpdateItemRequest(ItemType type, ItemStatus status, String name, Long price, String description) {
}
