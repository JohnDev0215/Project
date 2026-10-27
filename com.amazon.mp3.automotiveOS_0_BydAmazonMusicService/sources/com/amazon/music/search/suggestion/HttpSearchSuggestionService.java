package com.amazon.music.search.suggestion;

import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.tenzing.textsearch.v1_1.SuggestRequest;
import com.amazon.tenzing.textsearch.v1_1.SuggestResponse;
import com.amazon.tenzing.textsearch.v1_1.external.SuggestCall;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URL;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class HttpSearchSuggestionService implements SearchSuggestionService {
    private static final ObjectMapper MAPPER = Mappers.getMapper();
    private final SearchSuggestionServiceConfiguration configuration;
    private RetryPolicy retryPolicy = new DefaultRetryPolicy(20000, 1, 1.0f);

    public HttpSearchSuggestionService(SearchSuggestionServiceConfiguration searchSuggestionServiceConfiguration) {
        Validate.notNull(searchSuggestionServiceConfiguration, "Configuration cannot be null", new Object[0]);
        this.configuration = searchSuggestionServiceConfiguration;
    }

    @Override // com.amazon.music.search.suggestion.SearchSuggestionService
    public SuggestResponse query(SuggestRequest suggestRequest) throws VolleyError {
        URL searchURL = this.configuration.getSearchURL();
        RequestInterceptor requestInterceptor = this.configuration.getRequestInterceptor();
        suggestRequest.setMusicTerritory(this.configuration.getMusicTerritory());
        suggestRequest.setDeviceId(this.configuration.getDeviceId());
        suggestRequest.setDeviceType(this.configuration.getDeviceType());
        SuggestCall suggestCall = new SuggestCall(searchURL, suggestRequest, requestInterceptor);
        setRetryPolicy(suggestCall);
        return suggestCall.execute(MAPPER);
    }

    private void setRetryPolicy(CoralCall coralCall) {
        RetryPolicy retryPolicy = this.retryPolicy;
        if (retryPolicy != null) {
            coralCall.setRetryPolicy(retryPolicy);
        }
    }
}
