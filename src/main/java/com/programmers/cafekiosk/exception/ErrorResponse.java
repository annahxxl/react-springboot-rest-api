package com.programmers.cafekiosk.exception;

public record ErrorResponse(Integer status, String message) {

    public static ErrorResponse of(Integer status, String message) {
        return new ErrorResponse(status, message);
    }
}
