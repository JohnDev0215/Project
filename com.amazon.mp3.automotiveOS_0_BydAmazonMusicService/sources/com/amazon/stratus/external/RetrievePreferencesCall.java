package com.amazon.stratus.external;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.stratus.RetrievePreferencesRequest;
import com.amazon.stratus.RetrievePreferencesResponse;
import java.net.URL;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class RetrievePreferencesCall extends CoralCall<RetrievePreferencesRequest, RetrievePreferencesResponse> {
    public RetrievePreferencesCall(URL url, RetrievePreferencesRequest retrievePreferencesRequest, RequestInterceptor requestInterceptor) {
        this(url, retrievePreferencesRequest, requestInterceptor, false);
    }

    public RetrievePreferencesCall(URL url, RetrievePreferencesRequest retrievePreferencesRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, retrievePreferencesRequest, requestInterceptor, z);
    }

    public RetrievePreferencesCall(URL url, RetrievePreferencesRequest retrievePreferencesRequest, List<RequestInterceptor> list) {
        this(url, retrievePreferencesRequest, list, false);
    }

    public RetrievePreferencesCall(URL url, RetrievePreferencesRequest retrievePreferencesRequest, List<RequestInterceptor> list, boolean z) {
        super(url, retrievePreferencesRequest, list, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new RetrievePreferencesApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<RetrievePreferencesResponse> getResponseType() {
        return RetrievePreferencesResponse.class;
    }
}
