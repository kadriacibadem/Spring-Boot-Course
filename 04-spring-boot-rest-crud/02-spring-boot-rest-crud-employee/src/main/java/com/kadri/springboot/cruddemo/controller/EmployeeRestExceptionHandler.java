package com.kadri.springboot.cruddemo.controller;

import com.kadri.springboot.cruddemo.errors.EmployeeNotFoundException;
import com.kadri.springboot.cruddemo.response.EmployeeErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleException(EmployeeNotFoundException exc){
        EmployeeErrorResponse error = new EmployeeErrorResponse();
        error.setStatus(404);
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, org.springframework.http.HttpStatus.NOT_FOUND);
    }
}
