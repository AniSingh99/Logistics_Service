package com.logistic.parcel.config;

import com.logistic.parcel.constant.RulePriority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeliveryRule {
    private double highest;
    private double lowest;
    private double costValue;
    private RulePriority ruleName;
}
