package com.logistic.parcel.service;

import com.logistic.parcel.bean.response.VoucherResponse;
import com.logistic.parcel.constant.VoucherCode;
import com.logistic.parcel.exception.ParcelException;
import com.logistic.parcel.util.feign.VoucherFeignClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {VoucherServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class VoucherServiceImplTest {

    @Autowired
    private VoucherServiceImpl voucherService;

    @MockBean
    private VoucherFeignClient voucherFeignClient;

    @Test
    void testGetVoucherDiscount() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = "2022-01-01";
        Date expiry = dateFormat.parse(dateString);
        VoucherResponse voucherResponse = new VoucherResponse();
        voucherResponse.setCode(VoucherCode.GFI);
        voucherResponse.setDiscount(10.00);
        voucherResponse.setExipry(expiry);
        when(voucherFeignClient.getVoucherDiscount((VoucherCode) any(), anyString())).thenReturn(voucherResponse);
        double response = voucherService.getVoucherDiscount(VoucherCode.GFI);
        assertNotNull(response);
    }

    @Test
    void testGetVoucherDiscount2() {
        when(voucherFeignClient.getVoucherDiscount((VoucherCode) any(), anyString())).thenThrow(ParcelException.class);
        double response = voucherService.getVoucherDiscount(VoucherCode.GFI);
        assertEquals(0, response);
    }
}
