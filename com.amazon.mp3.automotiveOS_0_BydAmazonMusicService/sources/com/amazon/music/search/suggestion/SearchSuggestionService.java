package com.amazon.music.search.suggestion;

import com.amazon.tenzing.textsearch.v1_1.SuggestRequest;
import com.amazon.tenzing.textsearch.v1_1.SuggestResponse;
import com.android.volley.VolleyError;

/* JADX INFO: loaded from: classes4.dex */
public interface SearchSuggestionService {
    SuggestResponse query(SuggestRequest suggestRequest) throws VolleyError;
}
