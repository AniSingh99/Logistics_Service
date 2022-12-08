package com.logistic.parcel.bean.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParcelDataRequest {
    @Schema(example = "10cm3")
    private Double parcelLength;
    @Schema(example = "10cm3")
    private Double parcelWidth;
    @Schema(example = "10cm3")
    private Double parcelHeight;
    @Schema(example = "10kg")
    private Double parcelWeight;
}
