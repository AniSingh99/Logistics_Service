package com.logistic.parcel.bean.response;

import com.logistic.parcel.constant.VoucherCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VoucherResponse {
    private VoucherCode code;
    private double discount;
    private Date exipry;
}
