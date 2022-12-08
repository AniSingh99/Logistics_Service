package com.logistic.parcel.service;

import com.logistic.parcel.bean.request.ParcelDataRequest;
import com.logistic.parcel.bean.response.ParcelDataResponse;

public interface ParcelService {

    ParcelDataResponse getDeliveryCost(ParcelDataRequest parcelDataRequest, String voucherCode);
}
