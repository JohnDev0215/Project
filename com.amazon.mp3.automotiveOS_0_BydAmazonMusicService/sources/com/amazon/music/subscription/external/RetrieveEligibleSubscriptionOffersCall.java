package com.amazon.music.subscription.external;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.subscription.RetrieveEligibleSubscriptionOffersRequest;
import com.amazon.music.subscription.RetrieveEligibleSubscriptionOffersResponse;
import java.net.URL;

/* JADX INFO: loaded from: classes4.dex */
public class RetrieveEligibleSubscriptionOffersCall extends CoralCall<RetrieveEligibleSubscriptionOffersRequest, RetrieveEligibleSubscriptionOffersResponse> {
    public RetrieveEligibleSubscriptionOffersCall(URL url, RetrieveEligibleSubscriptionOffersRequest retrieveEligibleSubscriptionOffersRequest, RequestInterceptor requestInterceptor) {
        this(url, retrieveEligibleSubscriptionOffersRequest, requestInterceptor, false);
    }

    public RetrieveEligibleSubscriptionOffersCall(URL url, RetrieveEligibleSubscriptionOffersRequest retrieveEligibleSubscriptionOffersRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, retrieveEligibleSubscriptionOffersRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new RetrieveEligibleSubscriptionOffersApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<RetrieveEligibleSubscriptionOffersResponse> getResponseType() {
        return RetrieveEligibleSubscriptionOffersResponse.class;
    }
}
