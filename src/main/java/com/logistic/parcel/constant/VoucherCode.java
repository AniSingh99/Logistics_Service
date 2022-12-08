package com.logistic.parcel.constant;

public enum VoucherCode {
    MYNT(1),
    GFI(2),
    skdlks(3);

    private final int voucherValue;


    VoucherCode(int voucherValue) {
        this.voucherValue = voucherValue;
    }

    public int getVoucherValue() {
        return voucherValue;
    }
}
