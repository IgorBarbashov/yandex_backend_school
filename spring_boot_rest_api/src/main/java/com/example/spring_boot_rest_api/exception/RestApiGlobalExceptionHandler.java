package com.example.spring_boot_rest_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

@ControllerAdvice
public class RestApiGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String REST_API_NOT_FOUND_MESSAGE = "Item not found";

    private static final String REST_API_INVALID_DATA_MESSAGE = "Validation Failed";

    @ExceptionHandler(value = {
            ConstraintViolationException.class,
            RestApiInvalidDataException.class
    })
    public ResponseEntity<RestApiErrorResponse> handleException(ValidationException e) {
        RestApiErrorResponse data = new RestApiErrorResponse();
        data.setCode(HttpStatus.BAD_REQUEST.value());
        data.setMessage(REST_API_INVALID_DATA_MESSAGE);
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler()
    public ResponseEntity<RestApiErrorResponse> handleException(RestApiNotFoundException e) {
        RestApiErrorResponse data = new RestApiErrorResponse();
        data.setCode(HttpStatus.NOT_FOUND.value());
        data.setMessage(REST_API_NOT_FOUND_MESSAGE);
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }
}
