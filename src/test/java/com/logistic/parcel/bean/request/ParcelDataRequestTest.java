package com.logistic.parcel.bean.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParcelDataRequestTest {

    @Test
    void testConstructor() {
        ParcelDataRequest parcelDataRequest = new ParcelDataRequest(10D, 10D, 10D, 20D);
        parcelDataRequest.setParcelHeight(10D);
        parcelDataRequest.setParcelLength(10D);
        parcelDataRequest.setParcelWidth(10D);
        parcelDataRequest.setParcelWeight(20D);

        assertEquals(10D, parcelDataRequest.getParcelLength());
        assertEquals(10D, parcelDataRequest.getParcelWidth());
        assertEquals(10D, parcelDataRequest.getParcelHeight());
        assertEquals(20D, parcelDataRequest.getParcelWeight());
    }
}
