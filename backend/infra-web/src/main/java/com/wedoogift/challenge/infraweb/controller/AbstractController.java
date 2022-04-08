package com.wedoogift.challenge.infraweb.controller;

import com.wedoogift.challenge.domain.model.error.BusinessException;
import com.wedoogift.challenge.domain.model.error.ErrorType;
import com.wedoogift.challenge.infraweb.model.ErrorResponse;
import com.wedoogift.challenge.infraweb.model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractController {

    private final Logger logger = LoggerFactory.getLogger(AbstractController.class);

    @Autowired
    private Validator validator;

    protected void validate(Request request){
        Set<ConstraintViolation<Request>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            throw new BusinessException(ErrorType.BAD_REQUEST, violations.stream().map(violation ->
                    violation.getPropertyPath() + ": " + violation.getMessage()
            ).collect(Collectors.joining(", ")));
        }
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(
            BusinessException exception
    ) {
        HttpStatus status;
        switch(exception.getError()){
            case NOT_FOUND:
                status = HttpStatus.NOT_FOUND;
                break;
            case BAD_REQUEST:
                status = HttpStatus.BAD_REQUEST;
                break;
            case INSUFFICIENT_CREDIT:
                status = HttpStatus.PRECONDITION_FAILED;
                break;
            default:
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                break;
        }
        return ResponseEntity
                .status(status)
                .body(new ErrorResponse(exception));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleUnexpectedException(
            Exception exception
    ) {
        logger.error(exception.getLocalizedMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(ErrorType.INTERNAL_ERROR));
    }
}
