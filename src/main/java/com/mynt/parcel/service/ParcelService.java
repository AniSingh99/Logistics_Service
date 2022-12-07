package com.mynt.parcel.service;

import com.mynt.parcel.bean.request.ParcelDataRequest;
import com.mynt.parcel.bean.response.ParcelDataResponse;
import com.mynt.parcel.constant.VoucherCode;

public interface ParcelService {

    ParcelDataResponse getDeliveryCost(ParcelDataRequest parcelDataRequest, VoucherCode voucherCode);
}
