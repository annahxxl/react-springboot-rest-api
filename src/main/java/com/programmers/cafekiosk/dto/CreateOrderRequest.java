package com.programmers.cafekiosk.dto;

import java.util.List;

public record CreateOrderRequest(List<CreateOrderItemRequest> orderitems) {
}
