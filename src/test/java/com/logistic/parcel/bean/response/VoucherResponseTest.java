package com.logistic.parcel.bean.response;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VoucherResponseTest {
    @Test
    void testConstructor() throws ParseException {
        VoucherResponse voucherResponse = new VoucherResponse("XXX", 10.00, "2022-01-01");
        voucherResponse.setCode("XXX");
        voucherResponse.setDiscount(10.00);
        voucherResponse.setExpiry("2022-01-01");
        assertEquals(10.00, voucherResponse.getDiscount());
        assertEquals("XXX", voucherResponse.getCode());
        assertEquals("2022-01-01", voucherResponse.getExpiry());
    }

    @Test
    void testConstructor2() throws ParseException {;
        VoucherResponse voucherResponse = new VoucherResponse();
        voucherResponse.setCode("XXX");
        voucherResponse.setDiscount(10.00);
        voucherResponse.setExpiry("2022-01-01");
        assertEquals(10.00, voucherResponse.getDiscount());
        assertEquals("XXX", voucherResponse.getCode());
        assertEquals("2022-01-01", voucherResponse.getExpiry());
    }
}
