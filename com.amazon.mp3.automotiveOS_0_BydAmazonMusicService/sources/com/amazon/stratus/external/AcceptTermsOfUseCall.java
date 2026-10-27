package com.amazon.stratus.external;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.stratus.AcceptTermsOfUseRequest;
import java.net.URL;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AcceptTermsOfUseCall extends CoralCall<AcceptTermsOfUseRequest, Void> {
    public AcceptTermsOfUseCall(URL url, AcceptTermsOfUseRequest acceptTermsOfUseRequest, List<RequestInterceptor> list) {
        this(url, acceptTermsOfUseRequest, list, false);
    }

    public AcceptTermsOfUseCall(URL url, AcceptTermsOfUseRequest acceptTermsOfUseRequest, List<RequestInterceptor> list, boolean z) {
        super(url, acceptTermsOfUseRequest, list, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new AcceptTermsOfUseApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<Void> getResponseType() {
        return Void.class;
    }
}
