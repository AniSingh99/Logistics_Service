package com.mynt.parcel.bean.response;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class BaseResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object response;
    private String statusMessage;
    private int statusCode;
}
