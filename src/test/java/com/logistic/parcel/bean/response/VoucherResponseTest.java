package com.logistic.parcel.bean.response;

import com.logistic.parcel.constant.VoucherCode;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VoucherResponseTest {
    @Test
    void testConstructor() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = "2022-01-01";
        Date expiry = dateFormat.parse(dateString);
        VoucherResponse voucherResponse = new VoucherResponse(VoucherCode.GFI, 10.00, expiry);
        voucherResponse.setCode(VoucherCode.GFI);
        voucherResponse.setDiscount(10.00);
        voucherResponse.setExipry(expiry);
        assertEquals(10.00, voucherResponse.getDiscount());
        assertEquals(VoucherCode.GFI, voucherResponse.getCode());
        assertEquals(expiry, voucherResponse.getExipry());
    }

    @Test
    void testConstructor2() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = "2022-01-01";
        Date expiry = dateFormat.parse(dateString);
        VoucherResponse voucherResponse = new VoucherResponse();
        voucherResponse.setCode(VoucherCode.GFI);
        voucherResponse.setDiscount(10.00);
        voucherResponse.setExipry(expiry);
        assertEquals(10.00, voucherResponse.getDiscount());
        assertEquals(VoucherCode.GFI, voucherResponse.getCode());
        assertEquals(expiry, voucherResponse.getExipry());
    }
}
