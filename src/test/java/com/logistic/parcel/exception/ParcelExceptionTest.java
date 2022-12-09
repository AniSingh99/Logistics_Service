package com.logistic.parcel.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class ParcelExceptionTest {
    @Test
    void testConstructor() {
        ParcelException actualParcelException = new ParcelException("An error occurred");
        assertNull(actualParcelException.getCause());
        assertEquals(0, actualParcelException.getSuppressed().length);
        assertEquals("An error occurred", actualParcelException.getMessage());
        assertEquals("An error occurred", actualParcelException.getLocalizedMessage());
    }
}

