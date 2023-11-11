package com.programmers.cafekiosk.dto;

import com.programmers.cafekiosk.entity.ItemStatus;
import com.programmers.cafekiosk.entity.ItemType;

import java.util.Set;

public record GetItemsRequest(ItemType type, ItemStatus status, Set<Long> ids) {
}
