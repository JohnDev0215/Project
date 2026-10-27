package com.amazon.music.subscription.external;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.subscription.EvaluateSubscriptionCreationRequest;
import com.amazon.music.subscription.EvaluateSubscriptionCreationResponse;
import java.net.URL;

/* JADX INFO: loaded from: classes4.dex */
public class EvaluateSubscriptionCreationCall extends CoralCall<EvaluateSubscriptionCreationRequest, EvaluateSubscriptionCreationResponse> {
    public EvaluateSubscriptionCreationCall(URL url, EvaluateSubscriptionCreationRequest evaluateSubscriptionCreationRequest, RequestInterceptor requestInterceptor) {
        this(url, evaluateSubscriptionCreationRequest, requestInterceptor, false);
    }

    public EvaluateSubscriptionCreationCall(URL url, EvaluateSubscriptionCreationRequest evaluateSubscriptionCreationRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, evaluateSubscriptionCreationRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new EvaluateSubscriptionCreationApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<EvaluateSubscriptionCreationResponse> getResponseType() {
        return EvaluateSubscriptionCreationResponse.class;
    }
}
