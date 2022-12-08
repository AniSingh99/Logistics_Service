package com.logistic.parcel.service;

import com.logistic.parcel.constant.VoucherCode;
import com.logistic.parcel.util.feign.VoucherFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.logistic.parcel.constant.ParcelConstant.KEY_VALUE;

@Service
@AllArgsConstructor
public class VoucherServiceImpl implements VoucherService {

    private VoucherFeignClient voucherFeignClient;

    @Override
    public double getVoucherDiscount(VoucherCode voucherCode) {
        try {
            return voucherFeignClient.getVoucherDiscount(voucherCode, KEY_VALUE).getDiscount();
        } catch (Exception e) {
            return 0;
        }
    }
}
