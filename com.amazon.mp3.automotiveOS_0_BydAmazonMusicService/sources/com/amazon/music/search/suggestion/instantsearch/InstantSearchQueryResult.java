package com.amazon.music.search.suggestion.instantsearch;

import com.amazon.tenzing.textsearch.v1_1.InstantSearchResult;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class InstantSearchQueryResult {
    private final String queryId;
    private final List<InstantSearchResult> results;

    private InstantSearchQueryResult(List<InstantSearchResult> list, String str) {
        this.results = list;
        this.queryId = str;
    }

    public List<InstantSearchResult> getResults() {
        return this.results;
    }

    public String getQueryId() {
        return this.queryId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String queryId;
        private List<InstantSearchResult> results;

        public Builder withResults(List<InstantSearchResult> list) {
            this.results = list;
            return this;
        }

        public Builder withQueryId(String str) {
            this.queryId = str;
            return this;
        }

        public InstantSearchQueryResult build() {
            return new InstantSearchQueryResult(this.results, this.queryId);
        }
    }
}
