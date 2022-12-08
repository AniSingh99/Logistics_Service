package com.logistic.parcel.service;

import com.logistic.parcel.constant.VoucherCode;

public interface VoucherService {
    double getVoucherDiscount(VoucherCode voucherCode);
}
