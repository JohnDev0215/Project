package com.amazon.nimblymusicservice;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import java.net.URL;

/* JADX INFO: loaded from: classes5.dex */
public class GetRecentUnfilteredTrackActivityCall extends CoralCall<GetRecentUnfilteredTrackRequest, GetRecentUnfilteredTrackResponse> {
    public GetRecentUnfilteredTrackActivityCall(URL url, GetRecentUnfilteredTrackRequest getRecentUnfilteredTrackRequest, RequestInterceptor requestInterceptor) {
        this(url, getRecentUnfilteredTrackRequest, requestInterceptor, false);
    }

    public GetRecentUnfilteredTrackActivityCall(URL url, GetRecentUnfilteredTrackRequest getRecentUnfilteredTrackRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, getRecentUnfilteredTrackRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new GetRecentUnfilteredTrackActivityApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<GetRecentUnfilteredTrackResponse> getResponseType() {
        return GetRecentUnfilteredTrackResponse.class;
    }
}
