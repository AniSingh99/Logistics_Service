package com.mynt.parcel.service;

import com.mynt.parcel.bean.request.ParcelDataRequest;
import com.mynt.parcel.bean.response.ParcelDataResponse;
import com.mynt.parcel.config.DeliveryRule;
import com.mynt.parcel.config.ParcelValidationConfig;
import com.mynt.parcel.constant.RulePriority;
import com.mynt.parcel.constant.VoucherCode;
import com.mynt.parcel.exception.ParcelException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.mynt.parcel.constant.ExceptionConstant.REJECT_DELIVERY_COST;
import static com.mynt.parcel.constant.RulePriority.HEAVY_WEIGHT;
import static com.mynt.parcel.constant.RulePriority.LARGE_VOLUME;
import static com.mynt.parcel.constant.RulePriority.MEDIUM_VOLUME;
import static com.mynt.parcel.constant.RulePriority.SMALL_VOLUME;

@Service
@AllArgsConstructor
public class ParcelServiceImpl implements ParcelService {
    private ParcelValidationConfig parcelValidationConfig;
    private VoucherService voucherService;

    @Override
    public ParcelDataResponse getDeliveryCost(ParcelDataRequest parcelDataRequest, VoucherCode voucherCode) {
        double parcelVolume = getParcelVolume(parcelDataRequest);
        double parcelWeight = parcelDataRequest.getParcelWeight();
        double discountAmt = voucherService.getVoucherDiscount(voucherCode);
        double deliveryCharges;
        while (parcelWeight < parcelValidationConfig.getReject().getQuantity()) {
            // Need to pay delivery cost
            if (parcelWeight > parcelValidationConfig.getHeavy().getQuantity()) {
                //Heavy Parcel Weight exceeds 10kg
                deliveryCharges = getDeliveryCosts(parcelWeight, parcelValidationConfig.getHeavy(), discountAmt);
                return getParcelDeliveryCost(deliveryCharges, discountAmt, HEAVY_WEIGHT);
            } else { // Volume comparisons:
                if (parcelVolume < parcelValidationConfig.getSmall().getQuantity()) {
                    //Small Parcel Volume is less than 1500 cm3
                    deliveryCharges = getDeliveryCosts(parcelVolume, parcelValidationConfig.getSmall(), discountAmt);
                    return getParcelDeliveryCost(deliveryCharges, discountAmt, SMALL_VOLUME);
                }
                if ((parcelValidationConfig.getSmall().getQuantity() < parcelVolume)
                        && (parcelVolume < parcelValidationConfig.getMedium().getQuantity())) {
                    //Medium Parcel Volume is less than 2500 cm3
                    deliveryCharges = getDeliveryCosts(parcelVolume, parcelValidationConfig.getMedium(), discountAmt);
                    return getParcelDeliveryCost(deliveryCharges, discountAmt, MEDIUM_VOLUME);
                }
                if (parcelVolume > parcelValidationConfig.getMedium().getQuantity()) {
                    deliveryCharges = getDeliveryCosts(parcelVolume, parcelValidationConfig.getLarge(), discountAmt);
                    return getParcelDeliveryCost(deliveryCharges, discountAmt, LARGE_VOLUME);
                }
            }
        }
        throw new ParcelException(REJECT_DELIVERY_COST);
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
