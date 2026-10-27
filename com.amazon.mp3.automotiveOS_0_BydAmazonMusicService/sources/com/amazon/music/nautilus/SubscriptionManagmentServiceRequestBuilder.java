package com.amazon.music.nautilus;

import com.amazon.music.subscription.EvaluateSubscriptionCreationRequest;
import com.amazon.music.subscription.PriceWithCurrency;
import com.amazon.music.subscription.RetrieveEligibleSubscriptionOffersRequest;
import com.amazon.music.subscription.RetrieveSubscriptionsRequest;
import com.amazon.music.subscription.SubmitSubscriptionCreationRequest;
import com.amazon.stratus.PaymentInfo;
import java.util.Collections;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
class SubscriptionManagmentServiceRequestBuilder {
    private final MusicSubscriptionServiceConfiguration configuration;

    public SubscriptionManagmentServiceRequestBuilder(MusicSubscriptionServiceConfiguration musicSubscriptionServiceConfiguration) {
        this.configuration = (MusicSubscriptionServiceConfiguration) Validate.notNull(musicSubscriptionServiceConfiguration);
    }

    RetrieveEligibleSubscriptionOffersRequest buildEligibleSubscriptionOffersRequest() {
        RetrieveEligibleSubscriptionOffersRequest retrieveEligibleSubscriptionOffersRequest = new RetrieveEligibleSubscriptionOffersRequest();
        retrieveEligibleSubscriptionOffersRequest.setDeviceId(this.configuration.getDeviceId());
        retrieveEligibleSubscriptionOffersRequest.setDeviceType(this.configuration.getDeviceType());
        retrieveEligibleSubscriptionOffersRequest.setMusicTerritory(this.configuration.getMusicTerritory());
        retrieveEligibleSubscriptionOffersRequest.setIncludeIneligibleOffers(Boolean.FALSE);
        retrieveEligibleSubscriptionOffersRequest.setResponseFilters(Collections.singletonList("TRIAL"));
        return retrieveEligibleSubscriptionOffersRequest;
    }

    RetrieveSubscriptionsRequest buildRetrieveSubscriptionsRequest() {
        RetrieveSubscriptionsRequest retrieveSubscriptionsRequest = new RetrieveSubscriptionsRequest();
        retrieveSubscriptionsRequest.setDeviceId(this.configuration.getDeviceId());
        retrieveSubscriptionsRequest.setDeviceType(this.configuration.getDeviceType());
        retrieveSubscriptionsRequest.setActiveOnly(Boolean.TRUE);
        return retrieveSubscriptionsRequest;
    }

    EvaluateSubscriptionCreationRequest buildEvaluateSubscriptionCreationRequest(PaymentInfo paymentInfo, PriceWithCurrency priceWithCurrency, String str, String str2, String str3, String str4) {
        EvaluateSubscriptionCreationRequest evaluateSubscriptionCreationRequest = new EvaluateSubscriptionCreationRequest();
        evaluateSubscriptionCreationRequest.setDisplayPrice(priceWithCurrency);
        evaluateSubscriptionCreationRequest.setPaymentInfo(paymentInfo);
        evaluateSubscriptionCreationRequest.setScheduleId(str);
        evaluateSubscriptionCreationRequest.setSessionId(str2);
        evaluateSubscriptionCreationRequest.setAutoRenew(Boolean.TRUE);
        evaluateSubscriptionCreationRequest.setMusicTerritory(this.configuration.getMusicTerritory());
        evaluateSubscriptionCreationRequest.setDeviceType(this.configuration.getDeviceType());
        evaluateSubscriptionCreationRequest.setDeviceId(this.configuration.getDeviceId());
        if (!StringUtils.isEmpty(str3)) {
            evaluateSubscriptionCreationRequest.setAssociateTagId(str3);
        }
        if (!StringUtils.isEmpty(str4)) {
            evaluateSubscriptionCreationRequest.setEmpSku(str4);
        }
        return evaluateSubscriptionCreationRequest;
    }

    SubmitSubscriptionCreationRequest buildSubmitSubscriptionCreationRequest(PaymentInfo paymentInfo, String str, String str2) {
        SubmitSubscriptionCreationRequest submitSubscriptionCreationRequest = new SubmitSubscriptionCreationRequest();
        submitSubscriptionCreationRequest.setPaymentInfo(paymentInfo);
        submitSubscriptionCreationRequest.setOrderInitToken(str);
        submitSubscriptionCreationRequest.setEmpBillingDetails(str2);
        submitSubscriptionCreationRequest.setMusicTerritory(this.configuration.getMusicTerritory());
        submitSubscriptionCreationRequest.setDeviceType(this.configuration.getDeviceType());
        submitSubscriptionCreationRequest.setDeviceId(this.configuration.getDeviceId());
        return submitSubscriptionCreationRequest;
    }
}
