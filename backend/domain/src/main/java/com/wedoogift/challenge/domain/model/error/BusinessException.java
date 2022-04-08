package com.wedoogift.challenge.domain.model.error;

public class BusinessException extends RuntimeException {
    private final ErrorType error;


    public BusinessException(ErrorType error, String message) {
        this.error = error;
        this.message = message;
    }

    private String message;

    public BusinessException(ErrorType error) {
        this.error = error;
    }

    public ErrorType getError() {
        return error;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
