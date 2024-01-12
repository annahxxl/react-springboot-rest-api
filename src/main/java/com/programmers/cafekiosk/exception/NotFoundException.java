package com.programmers.cafekiosk.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private final Integer status = 404;

    public NotFoundException(String message) {
        super(message);
    }
}
