package com.amazon.musicensembleservice;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.storeservice.model.LookupRequest;
import com.amazon.music.storeservice.model.LookupResponse;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
public class LookupCall extends CoralCall<LookupRequest, LookupResponse> {
    public LookupCall(URL url, LookupRequest lookupRequest, RequestInterceptor requestInterceptor) {
        this(url, lookupRequest, requestInterceptor, false);
    }

    public LookupCall(URL url, LookupRequest lookupRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, lookupRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new LookupApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<LookupResponse> getResponseType() {
        return LookupResponse.class;
    }
}
