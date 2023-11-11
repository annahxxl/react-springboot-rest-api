package com.programmers.cafekiosk.dto.item.request;

import com.programmers.cafekiosk.dto.item.validator.ValidItemStatus;
import com.programmers.cafekiosk.dto.item.validator.ValidItemType;
import com.programmers.cafekiosk.entity.item.ItemStatus;
import com.programmers.cafekiosk.entity.item.ItemType;
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
