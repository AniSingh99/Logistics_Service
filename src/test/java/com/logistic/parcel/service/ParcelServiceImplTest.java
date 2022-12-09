package com.logistic.parcel.service;

import com.logistic.parcel.bean.request.ParcelDataRequest;
import com.logistic.parcel.bean.response.ParcelDataResponse;
import com.logistic.parcel.config.ParcelValidationConfig;
import com.logistic.parcel.exception.ParcelException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.Validation;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ContextConfiguration(classes = {ParcelServiceImpl.class, ParcelValidationConfig.class})
@SpringBootTest
@EnableConfigurationProperties(value = ParcelValidationConfig.class)
@ActiveProfiles("application-test")
@EnableAutoConfiguration
public class ParcelServiceImplTest {

    @Autowired
    private ParcelServiceImpl parcelService;


    @MockBean
    private VoucherServiceImpl voucherService;

    @Test
    void testGetDeliveryCost() {
        double discount = 0;
        when(voucherService.getVoucherDiscount(anyString())).thenReturn(discount);
        ParcelDataRequest parcelDataRequest = new ParcelDataRequest();
        parcelDataRequest.setParcelHeight(10D);
        parcelDataRequest.setParcelLength(10D);
        parcelDataRequest.setParcelWidth(10D);
        parcelDataRequest.setParcelWeight(20D);
        ParcelDataResponse parcelDataResponse = parcelService.getDeliveryCost(parcelDataRequest, "MYNT");
        assertNotNull(parcelDataResponse);
    }

    @Test
    void testGetDeliveryCost2() {
        double discount = 0;
        when(voucherService.getVoucherDiscount(anyString())).thenReturn(discount);
        ParcelDataRequest parcelDataRequest = new ParcelDataRequest();
        parcelDataRequest.setParcelHeight(10D);
        parcelDataRequest.setParcelLength(10D);
        parcelDataRequest.setParcelWidth(10D);
        parcelDataRequest.setParcelWeight(9D);
        ParcelDataResponse parcelDataResponse = parcelService.getDeliveryCost(parcelDataRequest, "MYNT");
        assertNotNull(parcelDataResponse);
    }

    @Test
    void testGetDeliveryCost3() {
        double discount = 0;
        when(voucherService.getVoucherDiscount(anyString())).thenReturn(discount);
        ParcelDataRequest parcelDataRequest = new ParcelDataRequest();
        parcelDataRequest.setParcelHeight(26D);
        parcelDataRequest.setParcelLength(10D);
        parcelDataRequest.setParcelWidth(10D);
        parcelDataRequest.setParcelWeight(9D);
        ParcelDataResponse parcelDataResponse = parcelService.getDeliveryCost(parcelDataRequest, "MYNT");
        assertNotNull(parcelDataResponse);
    }

    @Test
    void testGetDeliveryCost_throw_exception() {
        double discount = 0;
        when(voucherService.getVoucherDiscount(anyString())).thenReturn(discount);
        ParcelDataRequest parcelDataRequest = new ParcelDataRequest();
        parcelDataRequest.setParcelHeight(10D);
        parcelDataRequest.setParcelLength(null);
        parcelDataRequest.setParcelWidth(10D);
        parcelDataRequest.setParcelWeight(9D);
        assertThrows(ParcelException.class, () -> parcelService.getDeliveryCost(parcelDataRequest, "MYNT"));
    }
}
