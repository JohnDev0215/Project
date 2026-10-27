package com.amazon.stratus.external;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.stratus.UpdatePreferencesRequest;
import com.amazon.stratus.UpdatePreferencesResponse;
import java.net.URL;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class UpdatePreferencesCall extends CoralCall<UpdatePreferencesRequest, UpdatePreferencesResponse> {
    public UpdatePreferencesCall(URL url, UpdatePreferencesRequest updatePreferencesRequest, RequestInterceptor requestInterceptor) {
        this(url, updatePreferencesRequest, requestInterceptor, false);
    }

    public UpdatePreferencesCall(URL url, UpdatePreferencesRequest updatePreferencesRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, updatePreferencesRequest, requestInterceptor, z);
    }

    public UpdatePreferencesCall(URL url, UpdatePreferencesRequest updatePreferencesRequest, List<RequestInterceptor> list) {
        this(url, updatePreferencesRequest, list, false);
    }

    public UpdatePreferencesCall(URL url, UpdatePreferencesRequest updatePreferencesRequest, List<RequestInterceptor> list, boolean z) {
        super(url, updatePreferencesRequest, list, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new UpdatePreferencesApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<UpdatePreferencesResponse> getResponseType() {
        return UpdatePreferencesResponse.class;
    }
}
