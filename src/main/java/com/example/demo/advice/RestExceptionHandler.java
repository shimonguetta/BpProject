package com.example.demo.advice;

import com.example.demo.exception.InvalidEntityException;
import com.example.demo.exception.InvalidOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class RestExceptionHandler {

    @ExceptionHandler(value= {InvalidOperationException.class,InvalidEntityException.class})
    public ResponseEntity<?> handleInvalidOperationException(Exception ex){
        return ResponseEntity.badRequest()
                .body(new ErrorDto("Exception",ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }
}

