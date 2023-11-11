package com.programmers.cafekiosk.dto.item.request;

import com.programmers.cafekiosk.dto.item.validator.ValidItemStatus;
import com.programmers.cafekiosk.dto.item.validator.ValidItemType;
import com.programmers.cafekiosk.entity.item.ItemStatus;
import com.programmers.cafekiosk.entity.item.ItemType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateItemRequest(

        @NotBlank
        @ValidItemType
        ItemType type,

        @NotBlank
        @ValidItemStatus
        ItemStatus status,

        @NotBlank
        String name,

        @NotNull
        Long price,

        String description
) {
}
