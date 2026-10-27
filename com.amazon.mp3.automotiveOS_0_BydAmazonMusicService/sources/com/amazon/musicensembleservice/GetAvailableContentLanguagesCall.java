package com.amazon.musicensembleservice;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
public class GetAvailableContentLanguagesCall extends CoralCall<GetAvailableContentLanguagesRequest, GetAvailableContentLanguagesResponse> {
    public GetAvailableContentLanguagesCall(URL url, GetAvailableContentLanguagesRequest getAvailableContentLanguagesRequest, RequestInterceptor requestInterceptor) {
        this(url, getAvailableContentLanguagesRequest, requestInterceptor, false);
    }

    public GetAvailableContentLanguagesCall(URL url, GetAvailableContentLanguagesRequest getAvailableContentLanguagesRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, getAvailableContentLanguagesRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new GetAvailableContentLanguagesApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<GetAvailableContentLanguagesResponse> getResponseType() {
        return GetAvailableContentLanguagesResponse.class;
    }
}
