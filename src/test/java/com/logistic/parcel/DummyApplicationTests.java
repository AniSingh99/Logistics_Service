package com.logistic.parcel;

import com.logistic.parcel.controller.ParcelController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DummyApplicationTests {

    @Autowired
    private ParcelController parcelController;

    @Test
    void contextLoads() {
        Assertions.assertAll("Card service context load",
                () -> Assertions.assertNotNull(parcelController)
        );
    }

}
