package com.programmers.cafekiosk.dto;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ItemStatusValidator.class)
@Documented
public @interface ValidItemStatus {
    String message() default "Invalid ItemStatus";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
