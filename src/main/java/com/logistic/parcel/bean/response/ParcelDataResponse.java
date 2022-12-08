package com.logistic.parcel.bean.response;

import com.logistic.parcel.constant.RulePriority;
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
