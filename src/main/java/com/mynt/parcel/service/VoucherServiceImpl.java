package com.mynt.parcel.service;

import com.mynt.parcel.constant.VoucherCode;
import com.mynt.parcel.util.feign.VoucherFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.mynt.parcel.constant.ParcelConstant.KEY_VALUE;

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
