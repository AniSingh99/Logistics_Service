package com.logistic.parcel.util.feign;

import com.logistic.parcel.bean.response.VoucherResponse;
import com.logistic.parcel.constant.VoucherCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import static com.logistic.parcel.constant.ParcelConstant.KEY;
import static com.logistic.parcel.constant.ParcelConstant.VOUCHER_CODE;
import static com.logistic.parcel.constant.ParcelConstant.VOUCHER_URL;

@FeignClient(name = "Voucher-Feign-Service", url = "${connector.voucher-service.url}")
public interface VoucherFeignClient {

    @GetMapping(VOUCHER_URL)
    VoucherResponse getVoucherDiscount(@PathVariable(VOUCHER_CODE) VoucherCode voucherCode,
                                       @RequestParam(KEY) String key);
}
