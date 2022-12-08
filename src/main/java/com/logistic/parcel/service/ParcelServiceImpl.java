package com.logistic.parcel.service;

import com.logistic.parcel.bean.request.ParcelDataRequest;
import com.logistic.parcel.bean.response.ParcelDataResponse;
import com.logistic.parcel.config.DeliveryRule;
import com.logistic.parcel.config.ParcelValidationConfig;
import com.logistic.parcel.constant.ExceptionConstant;
import com.logistic.parcel.constant.RulePriority;
import com.logistic.parcel.constant.VoucherCode;
import com.logistic.parcel.exception.ParcelException;
import com.logistic.parcel.helper.Converter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ParcelServiceImpl implements ParcelService {
    private ParcelValidationConfig parcelValidationConfig;
    private VoucherService voucherService;

    @Override
    public ParcelDataResponse getDeliveryCost(ParcelDataRequest parcelDataRequest, String voucherCode) {
        double parcelVolume = getParcelVolume(parcelDataRequest);
        double parcelWeight = parcelDataRequest.getParcelWeight();
        double deliveryCharges;

        List<DeliveryRule> weightRules = Converter.getWeightRules(parcelValidationConfig);
        List<DeliveryRule> volumeRules = Converter.getVolumeRules(parcelValidationConfig);

        double discountAmt = (Objects.nonNull(voucherCode)) ?
                (voucherService.getVoucherDiscount(getRequestVoucherCode(voucherCode))) : 0;

        DeliveryRule weightDeliveryRule = weightRules.stream()
                .filter(deliveryRule -> ((deliveryRule.getHighest() > 0)?
                        (deliveryRule.getHighest() >= parcelWeight && parcelWeight >= deliveryRule.getLowest()):
                        (parcelWeight >= deliveryRule.getLowest())))
                .findAny().orElse(null);

        DeliveryRule volumeDeliveryRule = volumeRules.stream()
                .filter(deliveryRule -> ((deliveryRule.getLowest() > 0 && deliveryRule.getHighest() > 0)?
                (deliveryRule.getHighest() >= parcelVolume && parcelVolume >= deliveryRule.getLowest()):
                (parcelVolume >= deliveryRule.getLowest())))
                .findAny().orElse(null);

        if(Objects.nonNull(weightDeliveryRule)){
            deliveryCharges = getDeliveryCosts(parcelVolume, weightDeliveryRule, discountAmt);
            return getParcelDeliveryCost(deliveryCharges, discountAmt, weightDeliveryRule.getRuleName());
        }else {
            deliveryCharges = getDeliveryCosts(parcelVolume, volumeDeliveryRule, discountAmt);
            return getParcelDeliveryCost(deliveryCharges, discountAmt, volumeDeliveryRule.getRuleName());
        }

    }

    public VoucherCode getRequestVoucherCode(String voucherCode) {
        try {
            return VoucherCode.valueOf(voucherCode);
        } catch (Exception e) {
            return VoucherCode.skdlks;
        }
    }

    private ParcelDataResponse getParcelDeliveryCost(double deliveryCharges, double discountAmt, RulePriority rulePriority) {
        return ParcelDataResponse.builder()
                .deliveryCost(deliveryCharges)
                .priorityLevel(rulePriority)
                .discountAmt(discountAmt)
                .build();
    }

    private double getDeliveryCosts(double parcelWeight, DeliveryRule deliveryRule, double discountAmt) {
        return (deliveryRule.getCostValue() * parcelWeight) - discountAmt;
    }

    private double getParcelVolume(ParcelDataRequest parcelDataRequest) {
        return parcelDataRequest.getParcelLength() * parcelDataRequest.getParcelWidth() * parcelDataRequest.getParcelHeight();
    }
}
