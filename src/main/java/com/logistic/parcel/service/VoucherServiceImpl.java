package com.logistic.parcel.service;

import com.logistic.parcel.bean.response.VoucherResponse;
import com.logistic.parcel.util.feign.VoucherFeignClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.logistic.parcel.constant.ParcelConstant.KEY_VALUE;

@Service
@AllArgsConstructor
@Slf4j
public class VoucherServiceImpl implements VoucherService {

    private VoucherFeignClient voucherFeignClient;

    @Override
    public double getVoucherDiscount(String voucherCode) {
        try {
            // calling voucher service
            VoucherResponse voucherResponse = voucherFeignClient.getVoucherDiscount(voucherCode, KEY_VALUE);
            return getValidatedDiscount(voucherResponse);
        } catch (Exception e) {
            // return 0 discount if voucher code is invalid
            return 0;
        }
    }

    private double getValidatedDiscount(VoucherResponse voucherResponse) throws ParseException {
        // Making expiry validation
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date expiry = dateFormat.parse(voucherResponse.getExpiry());
        if (expiry.after(new Date())) {
            log.info("Vouhcer Valid, discount: {}", voucherResponse.getDiscount());
            return voucherResponse.getDiscount();
        } else {
            log.info("Voucher Validity exhausted, discount: {}", 0);
            return 0;
        }
    }
}
