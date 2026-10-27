package com.amazon.music.subscription.external;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.subscription.SubmitSubscriptionCreationRequest;
import com.amazon.music.subscription.SubmitSubscriptionCreationResponse;
import java.net.URL;

/* JADX INFO: loaded from: classes4.dex */
public class SubmitSubscriptionCreationCall extends CoralCall<SubmitSubscriptionCreationRequest, SubmitSubscriptionCreationResponse> {
    public SubmitSubscriptionCreationCall(URL url, SubmitSubscriptionCreationRequest submitSubscriptionCreationRequest, RequestInterceptor requestInterceptor) {
        this(url, submitSubscriptionCreationRequest, requestInterceptor, false);
    }

    public SubmitSubscriptionCreationCall(URL url, SubmitSubscriptionCreationRequest submitSubscriptionCreationRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, submitSubscriptionCreationRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new SubmitSubscriptionCreationApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<SubmitSubscriptionCreationResponse> getResponseType() {
        return SubmitSubscriptionCreationResponse.class;
    }
}
