package com.amazon.tenzing.textsearch.v1_1.external;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.tenzing.textsearch.v1_1.SearchRequest;
import com.amazon.tenzing.textsearch.v1_1.SearchResponse;
import java.net.URL;

/* JADX INFO: loaded from: classes5.dex */
public class SearchCall extends CoralCall<SearchRequest, SearchResponse> {
    public SearchCall(URL url, SearchRequest searchRequest, RequestInterceptor requestInterceptor) {
        this(url, searchRequest, requestInterceptor, false);
    }

    public SearchCall(URL url, SearchRequest searchRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, searchRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new SearchApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<SearchResponse> getResponseType() {
        return SearchResponse.class;
    }
}
