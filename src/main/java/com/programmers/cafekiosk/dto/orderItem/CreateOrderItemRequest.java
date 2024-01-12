package com.programmers.cafekiosk.dto.orderItem;

import jakarta.validation.constraints.NotNull;

public record CreateOrderItemRequest(

        @NotNull
        Long id,

        @NotNull
        Integer quantity
) {
}
