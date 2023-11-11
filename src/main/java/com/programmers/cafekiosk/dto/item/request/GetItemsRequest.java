package com.programmers.cafekiosk.dto.item.request;

import com.programmers.cafekiosk.dto.item.validator.ValidItemStatus;
import com.programmers.cafekiosk.dto.item.validator.ValidItemType;
import com.programmers.cafekiosk.entity.item.ItemStatus;
import com.programmers.cafekiosk.entity.item.ItemType;

import java.util.Set;

public record GetItemsRequest(

        @ValidItemType
        ItemType type,

        @ValidItemStatus
        ItemStatus status,

        Set<Long> ids
) {
}
