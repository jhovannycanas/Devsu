package com.devsu.control;

import com.devsu.exception.ResourceNotFound;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<Object> handleEntityNotFound(ResourceNotFound e) {
        return new ResponseEntity<Object>(new ErrorResponse(e.getMessage(), e.getCode()), HttpStatus.NOT_FOUND);
    }


    @Value
    @Data
    @AllArgsConstructor
    public class ErrorResponse {
        String mensaje;
        int code;
    }
}
