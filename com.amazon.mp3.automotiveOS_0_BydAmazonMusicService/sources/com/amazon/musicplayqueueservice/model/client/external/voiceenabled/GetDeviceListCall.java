package com.amazon.musicplayqueueservice.model.client.external.voiceenabled;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.musicplayqueueservice.client.common.GetDeviceListRequest;
import com.amazon.musicplayqueueservice.client.common.GetDeviceListResponse;
import java.net.URL;

/* JADX INFO: loaded from: classes5.dex */
public class GetDeviceListCall extends CoralCall<GetDeviceListRequest, GetDeviceListResponse> {
    public GetDeviceListCall(URL url, GetDeviceListRequest getDeviceListRequest, RequestInterceptor requestInterceptor) {
        this(url, getDeviceListRequest, requestInterceptor, false);
    }

    public GetDeviceListCall(URL url, GetDeviceListRequest getDeviceListRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, getDeviceListRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new GetDeviceListApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<GetDeviceListResponse> getResponseType() {
        return GetDeviceListResponse.class;
    }
}
