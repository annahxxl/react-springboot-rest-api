package com.programmers.cafekiosk.dto;

import jakarta.validation.constraints.NotNull;

public record CreateOrderItemRequest(

        @NotNull
        Long id,

        @NotNull
        Integer quantity
) {
}
