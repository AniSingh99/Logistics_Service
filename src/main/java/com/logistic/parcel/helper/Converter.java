package com.logistic.parcel.helper;

import com.logistic.parcel.config.DeliveryRule;
import com.logistic.parcel.config.ParcelValidationConfig;

import java.util.Arrays;
import java.util.List;

public class Converter {

    public static List<DeliveryRule> getWeightRules(ParcelValidationConfig parcelValidationConfig) {
        return Arrays.asList(parcelValidationConfig.getReject(),
                parcelValidationConfig.getHeavy());
    }

    public static List<DeliveryRule> getVolumeRules(ParcelValidationConfig parcelValidationConfig) {
        return Arrays.asList(parcelValidationConfig.getSmall(),
                parcelValidationConfig.getMedium(),
                parcelValidationConfig.getLarge());
    }
}
