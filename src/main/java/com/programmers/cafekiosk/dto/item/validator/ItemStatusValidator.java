package com.programmers.cafekiosk.dto.item.validator;

import com.programmers.cafekiosk.entity.item.ItemStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class ItemStatusValidator implements ConstraintValidator<ValidItemStatus, ItemStatus> {

    @Override
    public boolean isValid(ItemStatus itemStatus, ConstraintValidatorContext context) {
        if (itemStatus == null) {
            return true;
        }
        return Arrays.asList(ItemStatus.values()).contains(itemStatus);
    }
}