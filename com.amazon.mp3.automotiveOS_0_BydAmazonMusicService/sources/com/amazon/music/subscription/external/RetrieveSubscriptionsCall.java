package com.amazon.music.subscription.external;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.subscription.RetrieveSubscriptionsRequest;
import com.amazon.music.subscription.RetrieveSubscriptionsResponse;
import java.net.URL;

/* JADX INFO: loaded from: classes4.dex */
public class RetrieveSubscriptionsCall extends CoralCall<RetrieveSubscriptionsRequest, RetrieveSubscriptionsResponse> {
    public RetrieveSubscriptionsCall(URL url, RetrieveSubscriptionsRequest retrieveSubscriptionsRequest, RequestInterceptor requestInterceptor) {
        this(url, retrieveSubscriptionsRequest, requestInterceptor, false);
    }

    public RetrieveSubscriptionsCall(URL url, RetrieveSubscriptionsRequest retrieveSubscriptionsRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, retrieveSubscriptionsRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new RetrieveSubscriptionsApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<RetrieveSubscriptionsResponse> getResponseType() {
        return RetrieveSubscriptionsResponse.class;
    }
}
