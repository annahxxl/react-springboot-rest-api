package com.programmers.cafekiosk.dto;

import com.programmers.cafekiosk.entity.ItemStatus;
import com.programmers.cafekiosk.entity.ItemType;

public record GetItemsRequest(ItemType type, ItemStatus status) {
}
