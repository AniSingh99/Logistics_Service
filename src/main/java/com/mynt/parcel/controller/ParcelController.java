package com.mynt.parcel.controller;

import com.mynt.parcel.bean.request.ParcelDataRequest;
import com.mynt.parcel.bean.response.BaseResponse;
import com.mynt.parcel.bean.response.ParcelDataResponse;
import com.mynt.parcel.constant.VoucherCode;
import com.mynt.parcel.service.ParcelService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.mynt.parcel.constant.ParcelConstant.DELIVERY_COST_URL;
import static com.mynt.parcel.constant.ParcelConstant.PARCEL_ROOT_URl;
import static com.mynt.parcel.constant.ParcelConstant.SUCCESS_MSG;
import static com.mynt.parcel.constant.ParcelConstant.VOUCHER_CODE;

@RestController(PARCEL_ROOT_URl)
@AllArgsConstructor
@OpenAPIDefinition
public class ParcelController {
    private ParcelService parcelService;

    @Operation(summary = "Get Delivery Cost",
            description = "This API return the delivery estimates of a parcel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = BaseResponse.class),
                            mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @PostMapping(DELIVERY_COST_URL)
    public BaseResponse getDeliveryCost(@RequestBody
                                        @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                                description = "The Parcel Details",
                                                content = @Content(schema = @Schema(implementation = ParcelDataRequest.class)),
                                                required = true)
                                                ParcelDataRequest parcelDataRequest,
                                        @RequestParam(VOUCHER_CODE) VoucherCode voucherCode) {
        ParcelDataResponse deliveryCost = parcelService.getDeliveryCost(parcelDataRequest, voucherCode);
        return BaseResponse.builder()
                .response(deliveryCost)
                .statusMessage(SUCCESS_MSG)
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
