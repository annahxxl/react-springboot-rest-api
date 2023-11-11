package com.programmers.cafekiosk.dto.item.validator;

import com.programmers.cafekiosk.entity.item.ItemType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class ItemTypeValidator implements ConstraintValidator<ValidItemType, ItemType> {

    @Override
    public boolean isValid(ItemType itemType, ConstraintValidatorContext context) {
        if (itemType == null) {
            return true;
        }
        return Arrays.asList(ItemType.values()).contains(itemType);
    }
}

