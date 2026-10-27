package com.amazon.stratus.external;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.stratus.IsAccountValidRequest;
import com.amazon.stratus.IsAccountValidResponse;
import java.net.URL;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class IsAccountValidCall extends CoralCall<IsAccountValidRequest, IsAccountValidResponse> {
    public IsAccountValidCall(URL url, IsAccountValidRequest isAccountValidRequest, List<RequestInterceptor> list) {
        this(url, isAccountValidRequest, list, false);
    }

    public IsAccountValidCall(URL url, IsAccountValidRequest isAccountValidRequest, List<RequestInterceptor> list, boolean z) {
        super(url, isAccountValidRequest, list, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new IsAccountValidApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<IsAccountValidResponse> getResponseType() {
        return IsAccountValidResponse.class;
    }
}
