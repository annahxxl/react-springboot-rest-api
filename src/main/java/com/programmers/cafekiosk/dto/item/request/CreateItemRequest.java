package com.programmers.cafekiosk.dto.item.request;

import com.programmers.cafekiosk.dto.item.validator.ValidItemType;
import com.programmers.cafekiosk.entity.item.ItemType;
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
