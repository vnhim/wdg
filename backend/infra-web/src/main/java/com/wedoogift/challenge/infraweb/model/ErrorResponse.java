package com.wedoogift.challenge.infraweb.model;

import com.wedoogift.challenge.domain.model.error.BusinessException;
import com.wedoogift.challenge.domain.model.error.ErrorType;

public class ErrorResponse {
    private final String code;
    private final String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ErrorResponse(BusinessException exception){
        this.code = exception.getError().code;
        this.message = exception.getMessage() != null ? exception.getMessage() : exception.getError().message;
    }

    public ErrorResponse(ErrorType error){
        this.code = error.code;
        this.message = error.message;
    }

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
