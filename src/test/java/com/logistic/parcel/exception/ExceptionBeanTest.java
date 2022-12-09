package com.logistic.parcel.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ExceptionBeanTest {

    @Test
    void testConstructor() {
        ExceptionBean actualExceptionBean = new ExceptionBean();
        actualExceptionBean.setStatusCode(1);
        actualExceptionBean.setStatusMessage("An error occurred");
        assertEquals(1, actualExceptionBean.getStatusCode());
        assertEquals("An error occurred", actualExceptionBean.getStatusMessage());
    }

    @Test
    void testConstructor2() {
        ExceptionBean actualExceptionBean = new ExceptionBean(1, "An error occurred");
        actualExceptionBean.setStatusCode(1);
        actualExceptionBean.setStatusMessage("An error occurred");
        assertEquals(1, actualExceptionBean.getStatusCode());
        assertEquals("An error occurred", actualExceptionBean.getStatusMessage());
    }
}

