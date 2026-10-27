package com.amazon.nimblymusicservice;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import java.net.URL;

/* JADX INFO: loaded from: classes5.dex */
public class GetRecentTrackActivityCall extends CoralCall<GetRecentTrackActivityRequest, GetRecentTrackActivityResponse> {
    public GetRecentTrackActivityCall(URL url, GetRecentTrackActivityRequest getRecentTrackActivityRequest, RequestInterceptor requestInterceptor) {
        this(url, getRecentTrackActivityRequest, requestInterceptor, false);
    }

    public GetRecentTrackActivityCall(URL url, GetRecentTrackActivityRequest getRecentTrackActivityRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, getRecentTrackActivityRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new GetRecentTrackActivityApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<GetRecentTrackActivityResponse> getResponseType() {
        return GetRecentTrackActivityResponse.class;
    }
}
