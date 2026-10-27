package com.amazon.musicrelationshipservice.model;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import java.net.URL;

/* JADX INFO: loaded from: classes5.dex */
public class RemoveEdgesCall extends CoralCall<EdgesRequest, EdgesResponse> {
    public RemoveEdgesCall(URL url, EdgesRequest edgesRequest, RequestInterceptor requestInterceptor) {
        this(url, edgesRequest, requestInterceptor, false);
    }

    public RemoveEdgesCall(URL url, EdgesRequest edgesRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, edgesRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new RemoveEdgesApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<EdgesResponse> getResponseType() {
        return EdgesResponse.class;
    }
}
