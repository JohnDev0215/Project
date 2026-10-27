package com.amazon.tenzing.textsearch.v1_1.external;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.tenzing.textsearch.v1_1.InstantSearchRequest;
import com.amazon.tenzing.textsearch.v1_1.InstantSearchResponse;
import java.net.URL;

/* JADX INFO: loaded from: classes5.dex */
public class InstantSearchCall extends CoralCall<InstantSearchRequest, InstantSearchResponse> {
    public InstantSearchCall(URL url, InstantSearchRequest instantSearchRequest, RequestInterceptor requestInterceptor) {
        this(url, instantSearchRequest, requestInterceptor, false);
    }

    public InstantSearchCall(URL url, InstantSearchRequest instantSearchRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, instantSearchRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new InstantSearchApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<InstantSearchResponse> getResponseType() {
        return InstantSearchResponse.class;
    }
}
