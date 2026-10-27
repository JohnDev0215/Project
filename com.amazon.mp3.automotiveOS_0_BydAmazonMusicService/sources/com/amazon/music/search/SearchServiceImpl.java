package com.amazon.music.search;

import com.amazon.hermes.CoralCall;
import com.amazon.music.search.SearchService;
import com.amazon.tenzing.textsearch.v1_1.SearchResponse;
import com.amazon.tenzing.textsearch.v1_1.external.SearchCall;
import com.android.volley.DefaultRetryPolicy;

/* JADX INFO: loaded from: classes4.dex */
public final class SearchServiceImpl extends SearchService {
    public SearchServiceImpl(SearchService.Configuration configuration) {
        super(configuration);
    }

    @Override // com.amazon.music.search.SearchService
    protected CoralCall<com.amazon.tenzing.textsearch.v1_1.SearchRequest, SearchResponse> getSearchCall(com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequest) {
        SearchService.Configuration configuration = getConfiguration();
        SearchCall searchCall = new SearchCall(configuration.getTenzingTextSearchUrl(), searchRequest, configuration.getRequestInterceptor());
        searchCall.setRetryPolicy(new DefaultRetryPolicy(20000, 1, 1.0f));
        return searchCall;
    }
}
