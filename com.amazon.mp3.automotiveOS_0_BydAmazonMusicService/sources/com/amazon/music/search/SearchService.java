package com.amazon.music.search;

import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.tenzing.textsearch.v1_1.CustomerIdentity;
import com.amazon.tenzing.textsearch.v1_1.SearchResponse;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URL;
import java.util.Arrays;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public abstract class SearchService {
    private static final ObjectMapper MAPPER = Mappers.getMapper();
    private final Configuration mConfiguration;

    public interface Configuration {
        String getCustomerId();

        String getDeviceId();

        String getDeviceType();

        Locale getLanguageLocale();

        String getMusicRequestIdentityContextToken();

        String getMusicTerritory();

        RequestInterceptor getRequestInterceptor();

        URL getTenzingTextSearchUrl();
    }

    protected abstract CoralCall<com.amazon.tenzing.textsearch.v1_1.SearchRequest, SearchResponse> getSearchCall(com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequest);

    public SearchService(Configuration configuration) {
        this.mConfiguration = configuration;
    }

    protected Configuration getConfiguration() {
        return this.mConfiguration;
    }

    protected void addDeviceInfo(com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequest) {
        searchRequest.setMusicTerritory(this.mConfiguration.getMusicTerritory());
        searchRequest.setCustomerIdentity(CustomerIdentity.builder().withDeviceId(this.mConfiguration.getDeviceId()).withDeviceType(this.mConfiguration.getDeviceType()).withSessionId("123-1234567-5555555").withCustomerId(this.mConfiguration.getCustomerId()).withMusicRequestIdentityContextToken(this.mConfiguration.getMusicRequestIdentityContextToken()).build());
        searchRequest.setLocale(this.mConfiguration.getLanguageLocale().toString());
    }

    public SearchResponse search(com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequest) throws VolleyError {
        addDeviceInfo(searchRequest);
        if (searchRequest.getResultSpecs().isEmpty()) {
            return SearchResponse.builder().withResults(Arrays.asList(new com.amazon.tenzing.textsearch.v1_1.SearchResult[0])).build();
        }
        return getSearchCall(searchRequest).execute(MAPPER);
    }
}
