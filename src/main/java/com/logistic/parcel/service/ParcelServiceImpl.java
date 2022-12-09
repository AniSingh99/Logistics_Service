package com.logistic.parcel.service;

import com.logistic.parcel.bean.request.ParcelDataRequest;
import com.logistic.parcel.bean.response.ParcelDataResponse;
import com.logistic.parcel.config.DeliveryRule;
import com.logistic.parcel.config.ParcelValidationConfig;
import com.logistic.parcel.constant.RulePriority;
import com.logistic.parcel.exception.ParcelException;
import com.logistic.parcel.helper.Converter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

import static com.logistic.parcel.constant.ExceptionConstant.REQUEST_VALIDATION_ERROR;

@Service
@AllArgsConstructor
@Slf4j
public class ParcelServiceImpl implements ParcelService {
    private ParcelValidationConfig parcelValidationConfig;
    private VoucherService voucherService;

    @Override
    public ParcelDataResponse getDeliveryCost(ParcelDataRequest parcelDataRequest, String voucherCode) {
        makeRequestValidations(parcelDataRequest);
        double parcelVolume = getParcelVolume(parcelDataRequest);
        double parcelWeight = parcelDataRequest.getParcelWeight();
        log.info("parcelVolume: {}, parcelWeight: {}", parcelVolume, parcelWeight);
        double deliveryCharges;

        List<DeliveryRule> weightRules = Converter.getWeightRules(parcelValidationConfig);
        List<DeliveryRule> volumeRules = Converter.getVolumeRules(parcelValidationConfig);

        double discountPercent = (Objects.nonNull(voucherCode)) ?
                (voucherService.getVoucherDiscount(voucherCode)) : 0;
        log.info("discountAmt: {}", discountPercent);

        DeliveryRule weightDeliveryRule = weightRules.stream()
                .filter(deliveryRule -> ((deliveryRule.getHighest() > 0) ?
                        (deliveryRule.getHighest() >= parcelWeight && parcelWeight >= deliveryRule.getLowest()) :
                        (parcelWeight >= deliveryRule.getLowest())))
                .findAny().orElse(null);

        DeliveryRule volumeDeliveryRule = volumeRules.stream()
                .filter(deliveryRule -> ((deliveryRule.getLowest() > 0 && deliveryRule.getHighest() > 0) ?
                        (deliveryRule.getHighest() >= parcelVolume && parcelVolume >= deliveryRule.getLowest()) :
                        (parcelVolume >= deliveryRule.getLowest())))
                .findAny().orElse(null);

        // Weight rules precede over Volume rules
        if (Objects.nonNull(weightDeliveryRule)) {
            log.info("weightRule: {}", weightDeliveryRule);
            deliveryCharges = getDeliveryCosts(parcelWeight, weightDeliveryRule, discountPercent);
            return getParcelDeliveryCost(deliveryCharges, discountPercent, weightDeliveryRule.getRuleName());
        } else {
            log.info("volumeRule: {}", volumeDeliveryRule);
            deliveryCharges = getDeliveryCosts(parcelVolume, volumeDeliveryRule, discountPercent);
            return getParcelDeliveryCost(deliveryCharges, discountPercent, volumeDeliveryRule.getRuleName());
        }

    }

    private ParcelDataResponse getParcelDeliveryCost(double deliveryCharges, double discountAmt, RulePriority rulePriority) {
        return ParcelDataResponse.builder()
                .deliveryCost(makeDoubleValue(deliveryCharges))
                .priorityLevel(rulePriority)
                .discountAmt(discountAmt)
                .build();
    }

    private double getDeliveryCosts(double parcelWeight, DeliveryRule deliveryRule, double discountPercent) {
        return (deliveryRule.getCostValue() * parcelWeight) * ((100 - discountPercent) / 100);
    }

    private double getParcelVolume(ParcelDataRequest parcelDataRequest) {
        return parcelDataRequest.getParcelLength() * parcelDataRequest.getParcelWidth() * parcelDataRequest.getParcelHeight();
    }

    private static double makeDoubleValue(double number) {
        BigDecimal bd = new BigDecimal(number).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private void makeRequestValidations(ParcelDataRequest parcelDataRequest) {
        boolean lengthValidation = Objects.nonNull(parcelDataRequest.getParcelLength()) &&
                parcelDataRequest.getParcelLength() > 0;
        boolean widthValidation = Objects.nonNull(parcelDataRequest.getParcelWidth()) &&
                parcelDataRequest.getParcelWidth() > 0;
        boolean heightValidation = Objects.nonNull(parcelDataRequest.getParcelHeight()) &&
                parcelDataRequest.getParcelHeight() > 0;
        boolean weightValidation = Objects.nonNull(parcelDataRequest.getParcelWeight()) &&
                parcelDataRequest.getParcelWeight() > 0;
        if (lengthValidation && widthValidation && heightValidation && weightValidation) ;
        else {
            throw new ParcelException(REQUEST_VALIDATION_ERROR);
        }
    }
}
