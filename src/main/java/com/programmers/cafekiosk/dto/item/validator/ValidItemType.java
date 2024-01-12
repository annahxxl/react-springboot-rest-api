package com.programmers.cafekiosk.dto.item.validator;

import com.programmers.cafekiosk.dto.item.validator.ItemTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ItemTypeValidator.class)
@Documented
public @interface ValidItemType {
    String message() default "Invalid ItemType";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

