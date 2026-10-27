package com.amazon.tenzing.textsearch.v1_1.external;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.tenzing.textsearch.v1_1.SuggestRequest;
import com.amazon.tenzing.textsearch.v1_1.SuggestResponse;
import java.net.URL;

/* JADX INFO: loaded from: classes5.dex */
public class SuggestCall extends CoralCall<SuggestRequest, SuggestResponse> {
    public SuggestCall(URL url, SuggestRequest suggestRequest, RequestInterceptor requestInterceptor) {
        this(url, suggestRequest, requestInterceptor, false);
    }

    public SuggestCall(URL url, SuggestRequest suggestRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, suggestRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new SuggestApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<SuggestResponse> getResponseType() {
        return SuggestResponse.class;
    }
}
