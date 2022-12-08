package com.logistic.parcel.bean.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.http.HttpStatus;

public class BaseResponseTest {

    @Test
    void testConstructor(){
        BaseResponse baseResponse = new BaseResponse("xxx", "message", HttpStatus.OK.value());
        baseResponse.setResponse("xxx");
        baseResponse.setStatusMessage("message");
        baseResponse.setStatusCode(HttpStatus.OK.value());
        assertEquals("xxx", baseResponse.getResponse());
        assertEquals("message", baseResponse.getStatusMessage());
        assertEquals(HttpStatus.OK.value(), baseResponse.getStatusCode());
    }

    @Test
    void testConstructor2(){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResponse("xxx");
        baseResponse.setStatusMessage("message");
        baseResponse.setStatusCode(HttpStatus.OK.value());
        assertEquals("xxx", baseResponse.getResponse());
        assertEquals("message", baseResponse.getStatusMessage());
        assertEquals(HttpStatus.OK.value(), baseResponse.getStatusCode());
    }
}
