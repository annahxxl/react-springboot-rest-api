package com.programmers.cafekiosk.dto;

import com.programmers.cafekiosk.entity.ItemStatus;
import com.programmers.cafekiosk.entity.ItemType;
import jakarta.validation.constraints.NotNull;

public record UpdateItemRequest(

        @NotNull
        @ValidItemType
        ItemType type,

        @NotNull
        @ValidItemStatus
        ItemStatus status,

        @NotNull
        String name,

        @NotNull
        Long price,

        @NotNull
        String description
) {
}
