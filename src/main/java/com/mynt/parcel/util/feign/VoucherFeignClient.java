package com.mynt.parcel.util.feign;

import com.mynt.parcel.bean.request.VoucherResponse;
import com.mynt.parcel.constant.VoucherCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import static com.mynt.parcel.constant.ParcelConstant.KEY;
import static com.mynt.parcel.constant.ParcelConstant.VOUCHER_CODE;
import static com.mynt.parcel.constant.ParcelConstant.VOUCHER_URL;

@FeignClient(name = "Voucher-Feign-Service", url = "${connector.voucher-service.url}")
public interface VoucherFeignClient {

    @GetMapping(VOUCHER_URL)
    VoucherResponse getVoucherDiscount(@PathVariable(VOUCHER_CODE) VoucherCode voucherCode,
                                       @RequestParam(KEY) String key);
}
