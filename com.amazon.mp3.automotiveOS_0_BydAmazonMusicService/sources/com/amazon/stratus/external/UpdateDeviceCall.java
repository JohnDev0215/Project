package com.amazon.stratus.external;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.stratus.UpdateDeviceRequest;
import com.amazon.stratus.UpdateDeviceResponse;
import java.net.URL;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateDeviceCall extends CoralCall<UpdateDeviceRequest, UpdateDeviceResponse> {
    public UpdateDeviceCall(URL url, UpdateDeviceRequest updateDeviceRequest, List<RequestInterceptor> list) {
        this(url, updateDeviceRequest, list, false);
    }

    public UpdateDeviceCall(URL url, UpdateDeviceRequest updateDeviceRequest, List<RequestInterceptor> list, boolean z) {
        super(url, updateDeviceRequest, list, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new UpdateDeviceApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<UpdateDeviceResponse> getResponseType() {
        return UpdateDeviceResponse.class;
    }
}
