package com.amazon.musicplayqueueservice.model.client.external.voiceenabled;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.musicplayqueueservice.client.common.GetConnectionInfoRequest;
import com.amazon.musicplayqueueservice.client.common.GetConnectionInfoResponse;
import java.net.URL;

/* JADX INFO: loaded from: classes5.dex */
public class GetConnectionInfoCall extends CoralCall<GetConnectionInfoRequest, GetConnectionInfoResponse> {
    public GetConnectionInfoCall(URL url, GetConnectionInfoRequest getConnectionInfoRequest, RequestInterceptor requestInterceptor) {
        this(url, getConnectionInfoRequest, requestInterceptor, false);
    }

    public GetConnectionInfoCall(URL url, GetConnectionInfoRequest getConnectionInfoRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, getConnectionInfoRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new GetConnectionInfoApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<GetConnectionInfoResponse> getResponseType() {
        return GetConnectionInfoResponse.class;
    }
}
