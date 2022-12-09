package com.logistic.parcel.service;

import com.logistic.parcel.bean.response.VoucherResponse;
import com.logistic.parcel.exception.ParcelException;
import com.logistic.parcel.util.feign.VoucherFeignClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        VoucherResponse voucherResponse = new VoucherResponse();
        voucherResponse.setCode("XXX");
        voucherResponse.setDiscount(10.00);
        voucherResponse.setExpiry("2022-01-01");
        when(voucherFeignClient.getVoucherDiscount(anyString(), anyString())).thenReturn(voucherResponse);
        double response = voucherService.getVoucherDiscount("XXX");
        assertNotNull(response);
    }

    @Test
    void testGetVoucherDiscount2() throws ParseException {
        VoucherResponse voucherResponse = new VoucherResponse();
        voucherResponse.setCode("XXX");
        voucherResponse.setDiscount(10.00);
        voucherResponse.setExpiry("2023-01-01");
        when(voucherFeignClient.getVoucherDiscount(anyString(), anyString())).thenReturn(voucherResponse);
        double response = voucherService.getVoucherDiscount("XXX");
        assertNotNull(response);
    }

    @Test
    void testGetVoucherDiscount3() {
        when(voucherFeignClient.getVoucherDiscount(anyString(), anyString())).thenThrow(ParcelException.class);
        double response = voucherService.getVoucherDiscount("XXX");
        assertEquals(0, response);
    }
}
