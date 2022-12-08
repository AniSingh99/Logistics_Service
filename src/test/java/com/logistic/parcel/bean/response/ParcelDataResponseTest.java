package com.logistic.parcel.bean.response;

import com.logistic.parcel.constant.RulePriority;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParcelDataResponseTest {

    @Test
    void testConstructor() {
        ParcelDataResponse parcelDataResponse = new ParcelDataResponse(10.00, RulePriority.HEAVY_WEIGHT, 10.00);
        parcelDataResponse.setDeliveryCost(10.00);
        parcelDataResponse.setDiscountAmt(10.00);
        parcelDataResponse.setPriorityLevel(RulePriority.HEAVY_WEIGHT);
        assertEquals(10, 00, parcelDataResponse.getDeliveryCost());
        assertEquals(10, 00, parcelDataResponse.getDiscountAmt());
        assertEquals(RulePriority.HEAVY_WEIGHT, parcelDataResponse.getPriorityLevel());
    }

    @Test
    void testConstructor2() {
        ParcelDataResponse parcelDataResponse = new ParcelDataResponse();
        parcelDataResponse.setDeliveryCost(10.00);
        parcelDataResponse.setDiscountAmt(10.00);
        parcelDataResponse.setPriorityLevel(RulePriority.HEAVY_WEIGHT);
        assertEquals(10, 00, parcelDataResponse.getDeliveryCost());
        assertEquals(10, 00, parcelDataResponse.getDiscountAmt());
        assertEquals(RulePriority.HEAVY_WEIGHT, parcelDataResponse.getPriorityLevel());
    }
}
