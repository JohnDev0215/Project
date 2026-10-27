package com.amazon.nimblymusicservice;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import java.net.URL;

/* JADX INFO: loaded from: classes5.dex */
public class GetRecentActivityCall extends CoralCall<GetRecentActivityRequest, GetRecentActivityResponse> {
    public GetRecentActivityCall(URL url, GetRecentActivityRequest getRecentActivityRequest, RequestInterceptor requestInterceptor) {
        this(url, getRecentActivityRequest, requestInterceptor, false);
    }

    public GetRecentActivityCall(URL url, GetRecentActivityRequest getRecentActivityRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, getRecentActivityRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new GetRecentActivityApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<GetRecentActivityResponse> getResponseType() {
        return GetRecentActivityResponse.class;
    }
}
