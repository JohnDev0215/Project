package com.amazon.music.nautilus;

import com.amazon.music.subscription.EvaluateSubscriptionCreationResponse;
import com.amazon.music.subscription.PriceWithCurrency;
import com.amazon.music.subscription.RetrieveEligibleSubscriptionOffersResponse;
import com.amazon.music.subscription.RetrieveSubscriptionsResponse;
import com.amazon.music.subscription.SubmitSubscriptionCreationResponse;
import com.amazon.stratus.PaymentInfo;
import com.android.volley.VolleyError;
import java.util.regex.Pattern;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class SubscriptionManagementService {
    private final MusicSubscriptionService musicSubscriptionService;
    private final SubscriptionManagmentServiceRequestBuilder requestBuilder;
    private final SubscriptionManagementServiceResponseParser responseParser;
    private static final Logger LOG = LoggerFactory.getLogger(SubscriptionManagementService.class.getSimpleName());
    private static final Pattern DSN_PATTERN = Pattern.compile("deviceId=\\w+");

    public SubscriptionManagementService(MusicSubscriptionService musicSubscriptionService, SubscriptionManagementServiceResponseParser subscriptionManagementServiceResponseParser, SubscriptionManagmentServiceRequestBuilder subscriptionManagmentServiceRequestBuilder) {
        this.musicSubscriptionService = (MusicSubscriptionService) Validate.notNull(musicSubscriptionService);
        this.responseParser = (SubscriptionManagementServiceResponseParser) Validate.notNull(subscriptionManagementServiceResponseParser);
        this.requestBuilder = (SubscriptionManagmentServiceRequestBuilder) Validate.notNull(subscriptionManagmentServiceRequestBuilder);
    }

    public SubscriptionOffers getSubscriptionOffersFromService() {
        RetrieveEligibleSubscriptionOffersResponse retrieveEligibleSubscriptionOffersResponseRetrieveEligibleSubscriptionOffers = retrieveEligibleSubscriptionOffers();
        if (retrieveEligibleSubscriptionOffersResponseRetrieveEligibleSubscriptionOffers == null) {
            return null;
        }
        return this.responseParser.parseSubscriptionOffers(retrieveEligibleSubscriptionOffersResponseRetrieveEligibleSubscriptionOffers);
    }

    public Subscription getSubscriptionFromService() {
        RetrieveSubscriptionsResponse retrieveSubscriptionsResponseRetrieveSubscriptions = retrieveSubscriptions();
        if (retrieveSubscriptionsResponseRetrieveSubscriptions == null) {
            return null;
        }
        return this.responseParser.parseSubscriptions(retrieveSubscriptionsResponseRetrieveSubscriptions);
    }

    private RetrieveEligibleSubscriptionOffersResponse retrieveEligibleSubscriptionOffers() {
        try {
            return this.musicSubscriptionService.retrieveEligibleSubscriptionOffers(this.requestBuilder.buildEligibleSubscriptionOffersRequest());
        } catch (VolleyError e) {
            LOG.error("Error retrieving eligible subscription offers: " + redactDSN(e));
            return null;
        }
    }

    private RetrieveSubscriptionsResponse retrieveSubscriptions() {
        try {
            return this.musicSubscriptionService.retrieveSubscriptions(this.requestBuilder.buildRetrieveSubscriptionsRequest());
        } catch (VolleyError e) {
            LOG.error("Error retrieving subscriptions: " + redactDSN(e));
            return null;
        }
    }

    private String redactDSN(Exception exc) {
        return DSN_PATTERN.matcher(ExceptionUtils.getStackTrace(exc)).replaceAll("deviceId=[REDACTED]");
    }

    public EvaluateSubscriptionCreationResponse evaluateSubscriptionFromService(PaymentInfo paymentInfo, PriceWithCurrency priceWithCurrency, String str, String str2, String str3, String str4) throws VolleyError {
        try {
            return this.musicSubscriptionService.evaluateSubscriptionCreation(this.requestBuilder.buildEvaluateSubscriptionCreationRequest(paymentInfo, priceWithCurrency, str, str2, str3, str4));
        } catch (VolleyError e) {
            LOG.error("Error calling evaluateSubscriptionCreation", (Throwable) e);
            throw e;
        }
    }

    public SubmitSubscriptionCreationResponse submitSubscriptionFromService(PaymentInfo paymentInfo, String str, String str2) throws VolleyError {
        try {
            return this.musicSubscriptionService.submitSubscriptionCreation(this.requestBuilder.buildSubmitSubscriptionCreationRequest(paymentInfo, str, str2));
        } catch (VolleyError e) {
            LOG.error("Error calling submitSubscriptionCreation ", (Throwable) e);
            throw e;
        }
    }
}
