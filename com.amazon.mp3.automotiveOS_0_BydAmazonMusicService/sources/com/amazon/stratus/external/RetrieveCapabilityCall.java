package com.amazon.stratus.external;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.stratus.RetrieveCapabilityRequest;
import com.amazon.stratus.RetrieveCapabilityResponse;
import java.net.URL;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class RetrieveCapabilityCall extends CoralCall<RetrieveCapabilityRequest, RetrieveCapabilityResponse> {
    public RetrieveCapabilityCall(URL url, RetrieveCapabilityRequest retrieveCapabilityRequest, List<RequestInterceptor> list) {
        this(url, retrieveCapabilityRequest, list, false);
    }

    public RetrieveCapabilityCall(URL url, RetrieveCapabilityRequest retrieveCapabilityRequest, List<RequestInterceptor> list, boolean z) {
        super(url, retrieveCapabilityRequest, list, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new RetrieveCapabilityApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<RetrieveCapabilityResponse> getResponseType() {
        return RetrieveCapabilityResponse.class;
    }
}
