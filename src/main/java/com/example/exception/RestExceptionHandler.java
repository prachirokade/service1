package com.example.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiError> handleException(Exception ex) {

        ApiError apiError = ApiError.builder().errorMessage(ex.getMessage()).build();
        ResponseEntity<ApiError> responseEntity = new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

    @ExceptionHandler(JsonMappingException.class)
    protected ResponseEntity<ApiError> handleJsonMappingException(Exception ex) {

        ApiError apiError = ApiError.builder().errorMessage(ex.getMessage()).build();
        ResponseEntity<ApiError> responseEntity = new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

}

