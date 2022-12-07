package com.mynt.parcel.advice;

import com.mynt.parcel.exception.ExceptionBean;
import com.mynt.parcel.exception.ParcelException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ParcelExceptionAdvice {

    @ExceptionHandler(ParcelException.class)
    public ResponseEntity<String> handleCustomException(ParcelException e) {
        Object result = new ExceptionBean(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
    }
}
