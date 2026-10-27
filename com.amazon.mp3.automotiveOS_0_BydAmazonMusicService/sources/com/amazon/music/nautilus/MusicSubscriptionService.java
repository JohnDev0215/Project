package com.amazon.music.nautilus;

import com.amazon.hermes.CoralCall;
import com.amazon.music.subscription.EvaluateSubscriptionCreationRequest;
import com.amazon.music.subscription.EvaluateSubscriptionCreationResponse;
import com.amazon.music.subscription.RetrieveEligibleSubscriptionOffersRequest;
import com.amazon.music.subscription.RetrieveEligibleSubscriptionOffersResponse;
import com.amazon.music.subscription.RetrieveSubscriptionsRequest;
import com.amazon.music.subscription.RetrieveSubscriptionsResponse;
import com.amazon.music.subscription.SubmitSubscriptionCreationRequest;
import com.amazon.music.subscription.SubmitSubscriptionCreationResponse;
import com.amazon.music.subscription.external.EvaluateSubscriptionCreationCall;
import com.amazon.music.subscription.external.RetrieveEligibleSubscriptionOffersCall;
import com.amazon.music.subscription.external.RetrieveSubscriptionsCall;
import com.amazon.music.subscription.external.SubmitSubscriptionCreationCall;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
class MusicSubscriptionService {
    private static final ObjectMapper MAPPER = Mappers.getMapper();
    private final MusicSubscriptionServiceConfiguration configuration;
    private RetryPolicy retryPolicy = new DefaultRetryPolicy(20000, 3, 1.0f);

    public MusicSubscriptionService(MusicSubscriptionServiceConfiguration musicSubscriptionServiceConfiguration) {
        this.configuration = (MusicSubscriptionServiceConfiguration) Validate.notNull(musicSubscriptionServiceConfiguration);
    }

    public RetrieveEligibleSubscriptionOffersResponse retrieveEligibleSubscriptionOffers(RetrieveEligibleSubscriptionOffersRequest retrieveEligibleSubscriptionOffersRequest) throws VolleyError {
        RetrieveEligibleSubscriptionOffersCall retrieveEligibleSubscriptionOffersCallBuildRetrieveEligibleSubscriptionOffersCall = buildRetrieveEligibleSubscriptionOffersCall(retrieveEligibleSubscriptionOffersRequest);
        setRetryPolicyForCall(retrieveEligibleSubscriptionOffersCallBuildRetrieveEligibleSubscriptionOffersCall);
        return retrieveEligibleSubscriptionOffersCallBuildRetrieveEligibleSubscriptionOffersCall.execute(MAPPER);
    }

    RetrieveEligibleSubscriptionOffersCall buildRetrieveEligibleSubscriptionOffersCall(RetrieveEligibleSubscriptionOffersRequest retrieveEligibleSubscriptionOffersRequest) {
        return new RetrieveEligibleSubscriptionOffersCall(this.configuration.getMusicSubscriptionUrl(), retrieveEligibleSubscriptionOffersRequest, this.configuration.getRequestInterceptor());
    }

    public RetrieveSubscriptionsResponse retrieveSubscriptions(RetrieveSubscriptionsRequest retrieveSubscriptionsRequest) throws VolleyError {
        RetrieveSubscriptionsCall retrieveSubscriptionsCallBuildRetrieveSubscriptionsCall = buildRetrieveSubscriptionsCall(retrieveSubscriptionsRequest);
        setRetryPolicyForCall(retrieveSubscriptionsCallBuildRetrieveSubscriptionsCall);
        return retrieveSubscriptionsCallBuildRetrieveSubscriptionsCall.execute(MAPPER);
    }

    RetrieveSubscriptionsCall buildRetrieveSubscriptionsCall(RetrieveSubscriptionsRequest retrieveSubscriptionsRequest) {
        return new RetrieveSubscriptionsCall(this.configuration.getMusicSubscriptionUrl(), retrieveSubscriptionsRequest, this.configuration.getRequestInterceptor());
    }

    private void setRetryPolicyForCall(CoralCall coralCall) {
        RetryPolicy retryPolicy = this.retryPolicy;
        if (retryPolicy != null) {
            coralCall.setRetryPolicy(retryPolicy);
        }
    }

    public EvaluateSubscriptionCreationResponse evaluateSubscriptionCreation(EvaluateSubscriptionCreationRequest evaluateSubscriptionCreationRequest) throws VolleyError {
        EvaluateSubscriptionCreationCall evaluateSubscriptionCreationCallBuildEvaluateSubscriptionCreationCall = buildEvaluateSubscriptionCreationCall(evaluateSubscriptionCreationRequest);
        setRetryPolicyForCall(evaluateSubscriptionCreationCallBuildEvaluateSubscriptionCreationCall);
        return evaluateSubscriptionCreationCallBuildEvaluateSubscriptionCreationCall.execute(MAPPER);
    }

    EvaluateSubscriptionCreationCall buildEvaluateSubscriptionCreationCall(EvaluateSubscriptionCreationRequest evaluateSubscriptionCreationRequest) {
        return new EvaluateSubscriptionCreationCall(this.configuration.getMusicSubscriptionUrl(), evaluateSubscriptionCreationRequest, this.configuration.getRequestInterceptor());
    }

    public SubmitSubscriptionCreationResponse submitSubscriptionCreation(SubmitSubscriptionCreationRequest submitSubscriptionCreationRequest) throws VolleyError {
        SubmitSubscriptionCreationCall submitSubscriptionCreationCallBuildSubmitSubscriptionCreationCall = buildSubmitSubscriptionCreationCall(submitSubscriptionCreationRequest);
        setRetryPolicyForCall(submitSubscriptionCreationCallBuildSubmitSubscriptionCreationCall);
        return submitSubscriptionCreationCallBuildSubmitSubscriptionCreationCall.execute(MAPPER);
    }

    SubmitSubscriptionCreationCall buildSubmitSubscriptionCreationCall(SubmitSubscriptionCreationRequest submitSubscriptionCreationRequest) {
        return new SubmitSubscriptionCreationCall(this.configuration.getMusicSubscriptionUrl(), submitSubscriptionCreationRequest, this.configuration.getRequestInterceptor());
    }
}
