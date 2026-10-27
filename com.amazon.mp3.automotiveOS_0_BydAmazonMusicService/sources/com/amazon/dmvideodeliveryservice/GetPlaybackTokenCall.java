package com.amazon.dmvideodeliveryservice;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
public class GetPlaybackTokenCall extends CoralCall<GetPlaybackTokenRequest, GetPlaybackTokenResponse> {
    public GetPlaybackTokenCall(URL url, GetPlaybackTokenRequest getPlaybackTokenRequest, RequestInterceptor requestInterceptor) {
        this(url, getPlaybackTokenRequest, requestInterceptor, false);
    }

    public GetPlaybackTokenCall(URL url, GetPlaybackTokenRequest getPlaybackTokenRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, getPlaybackTokenRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new GetPlaybackTokenApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<GetPlaybackTokenResponse> getResponseType() {
        return GetPlaybackTokenResponse.class;
    }
}
