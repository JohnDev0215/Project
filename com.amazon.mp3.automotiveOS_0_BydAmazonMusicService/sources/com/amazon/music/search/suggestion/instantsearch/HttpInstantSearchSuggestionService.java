package com.amazon.music.search.suggestion.instantsearch;

import com.amazon.hermes.CoralCall;
import com.amazon.tenzing.textsearch.v1_1.CustomerIdentity;
import com.amazon.tenzing.textsearch.v1_1.InstantSearchRequest;
import com.amazon.tenzing.textsearch.v1_1.InstantSearchResponse;
import com.amazon.tenzing.textsearch.v1_1.external.InstantSearchCall;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class HttpInstantSearchSuggestionService implements InstantSearchSuggestionService {
    private static final ObjectMapper MAPPER = Mappers.getMapper();
    private final InstantSearchSuggestionServiceConfiguration configuration;
    private final RetryPolicy retryPolicy = new DefaultRetryPolicy(20000, 1, 1.0f);

    public HttpInstantSearchSuggestionService(InstantSearchSuggestionServiceConfiguration instantSearchSuggestionServiceConfiguration) {
        Validate.notNull(instantSearchSuggestionServiceConfiguration, "Configuration cannot be null", new Object[0]);
        this.configuration = instantSearchSuggestionServiceConfiguration;
    }

    @Override // com.amazon.music.search.suggestion.instantsearch.InstantSearchSuggestionService
    public InstantSearchResponse query(InstantSearchRequest instantSearchRequest) throws Exception {
        if (this.configuration.getCustomerId() == null && instantSearchRequest.getEntitySpec() != null) {
            throw new Exception("customerId must be present when entitySpec is present");
        }
        instantSearchRequest.setCustomerIdentity(CustomerIdentity.builder().withCustomerId(this.configuration.getCustomerId()).withSessionId(this.configuration.getSessionId()).withDeviceId(this.configuration.getDeviceId()).withDeviceType(this.configuration.getDeviceType()).build());
        instantSearchRequest.setLocale(this.configuration.getLocale());
        instantSearchRequest.setMusicTerritory(this.configuration.getMusicTerritory());
        InstantSearchCall instantSearchCall = new InstantSearchCall(this.configuration.getSearchURL(), instantSearchRequest, this.configuration.getRequestInterceptor());
        setRetryPolicy(instantSearchCall);
        return instantSearchCall.execute(MAPPER);
    }

    private void setRetryPolicy(CoralCall coralCall) {
        RetryPolicy retryPolicy = this.retryPolicy;
        if (retryPolicy != null) {
            coralCall.setRetryPolicy(retryPolicy);
        }
    }
}
