package com.amazon.music.search.suggestion.instantsearch;

import com.amazon.tenzing.textsearch.v1_1.InstantSearchRequest;
import com.amazon.tenzing.textsearch.v1_1.InstantSearchResponse;

/* JADX INFO: loaded from: classes4.dex */
public interface InstantSearchSuggestionService {
    InstantSearchResponse query(InstantSearchRequest instantSearchRequest) throws Exception;
}
