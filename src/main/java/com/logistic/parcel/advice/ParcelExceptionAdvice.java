package com.logistic.parcel.advice;

import com.logistic.parcel.exception.ExceptionBean;
import com.logistic.parcel.exception.ParcelException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ParcelExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception e) {
        Object result = new ExceptionBean(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ParcelException.class)
    public ResponseEntity<String> handleCustomException(ParcelException e) {
        Object result = new ExceptionBean(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
    }
}
