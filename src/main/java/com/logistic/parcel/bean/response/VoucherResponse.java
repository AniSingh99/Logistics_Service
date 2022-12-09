package com.logistic.parcel.bean.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VoucherResponse {
    private String code;
    private double discount;
    private String expiry;
}
