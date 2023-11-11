package com.programmers.cafekiosk.dto;

import com.programmers.cafekiosk.entity.ItemType;
import jakarta.validation.constraints.NotNull;

public record CreateItemRequest(

        @NotNull
        @ValidItemType
        ItemType type,

        @NotNull
        String name,

        @NotNull
        Long price,

        @NotNull
        String description
) {
}
