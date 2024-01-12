package com.programmers.cafekiosk.dto.order;

import com.programmers.cafekiosk.dto.orderItem.CreateOrderItemRequest;

import java.util.List;

public record CreateOrderRequest(List<CreateOrderItemRequest> orderitems) {
}
