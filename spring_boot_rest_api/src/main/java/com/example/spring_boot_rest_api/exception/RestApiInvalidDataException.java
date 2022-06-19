package com.example.spring_boot_rest_api.exception;

import javax.validation.ValidationException;

public class RestApiInvalidDataException extends ValidationException {
    public RestApiInvalidDataException() { super(); }
}
