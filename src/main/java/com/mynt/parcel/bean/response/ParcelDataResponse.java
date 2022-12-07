package com.mynt.parcel.bean.response;

import com.mynt.parcel.constant.RulePriority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ParcelDataResponse {
    private double deliveryCost;
    private RulePriority priorityLevel;
    private double discountAmt;
}
