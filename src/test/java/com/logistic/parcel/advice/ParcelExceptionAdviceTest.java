package com.logistic.parcel.advice;

import com.logistic.parcel.exception.ParcelException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParcelExceptionAdviceTest {

    @Test
    void testHandleAllExceptions() {
        ParcelExceptionAdvice parcelExceptionAdvice = new ParcelExceptionAdvice();
        ResponseEntity<String> actualHandleAllExceptionsResult = parcelExceptionAdvice
                .handleAllExceptions(new Exception("foo"));
        assertTrue(actualHandleAllExceptionsResult.hasBody());
        assertTrue(actualHandleAllExceptionsResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleAllExceptionsResult.getStatusCode());
    }

    @Test
    void testHandleCustomException() {
        ParcelExceptionAdvice parcelExceptionAdvice = new ParcelExceptionAdvice();
        ResponseEntity<String> actualHandleAllExceptionsResult = parcelExceptionAdvice
                .handleAllExceptions(new ParcelException("foo"));
        assertTrue(actualHandleAllExceptionsResult.hasBody());
        assertTrue(actualHandleAllExceptionsResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleAllExceptionsResult.getStatusCode());
    }

}
