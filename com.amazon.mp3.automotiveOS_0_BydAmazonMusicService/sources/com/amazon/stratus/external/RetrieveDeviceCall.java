package com.amazon.stratus.external;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.stratus.RetrieveDeviceRequest;
import com.amazon.stratus.RetrieveDeviceResponse;
import java.net.URL;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class RetrieveDeviceCall extends CoralCall<RetrieveDeviceRequest, RetrieveDeviceResponse> {
    public RetrieveDeviceCall(URL url, RetrieveDeviceRequest retrieveDeviceRequest, List<RequestInterceptor> list) {
        this(url, retrieveDeviceRequest, list, false);
    }

    public RetrieveDeviceCall(URL url, RetrieveDeviceRequest retrieveDeviceRequest, List<RequestInterceptor> list, boolean z) {
        super(url, retrieveDeviceRequest, list, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new RetrieveDeviceApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<RetrieveDeviceResponse> getResponseType() {
        return RetrieveDeviceResponse.class;
    }
}
