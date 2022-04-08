package com.wedoogift.challenge.domain.model.error;

public enum ErrorType {
    NOT_FOUND("001", "NOT FOUND"),
    BAD_REQUEST("002", "BAD REQUEST"),

    INSUFFICIENT_CREDIT("100", "not enough credit"),

    INTERNAL_ERROR("999", "Sorry, something went wrong")
    ;

    public final String code;
    public final String message;

    ErrorType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
