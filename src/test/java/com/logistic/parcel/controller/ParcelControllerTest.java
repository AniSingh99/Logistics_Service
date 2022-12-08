package com.logistic.parcel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistic.parcel.bean.response.ParcelDataResponse;
import com.logistic.parcel.bean.request.ParcelDataRequest;
import com.logistic.parcel.service.ParcelService;
import com.logistic.parcel.service.ParcelServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.logistic.parcel.constant.ParcelConstant.DELIVERY_COST_URL;
import static com.logistic.parcel.constant.ParcelConstant.PARCEL_ROOT_URl;
import static com.logistic.parcel.constant.ParcelConstant.VOUCHER_CODE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ParcelController.class})
@ExtendWith(SpringExtension.class)
public class ParcelControllerTest {
    @Autowired
    private ParcelController parcelController;

    @MockBean
    private ParcelServiceImpl parcelService;

    @Test
    void testGetDeliveryCost() throws Exception {
        when(parcelService.getDeliveryCost((ParcelDataRequest) any(), anyString())).thenReturn(new ParcelDataResponse());
        ParcelDataRequest parcelDataRequest = new ParcelDataRequest();
        parcelDataRequest.setParcelHeight(10D);
        parcelDataRequest.setParcelLength(10D);
        parcelDataRequest.setParcelWidth(10D);
        parcelDataRequest.setParcelWeight(20D);
        String content = (new ObjectMapper()).writeValueAsString(parcelDataRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/parcel/delivery-cost")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .header(VOUCHER_CODE, VOUCHER_CODE);

        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.parcelController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(200));
    }
}
