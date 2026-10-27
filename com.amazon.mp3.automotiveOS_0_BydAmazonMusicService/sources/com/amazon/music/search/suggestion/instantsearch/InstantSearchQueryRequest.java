package com.amazon.music.search.suggestion.instantsearch;

import com.amazon.tenzing.textsearch.v1_1.InstantEntitySpecification;
import com.amazon.tenzing.textsearch.v1_1.InstantTextSpecification;

/* JADX INFO: loaded from: classes4.dex */
public class InstantSearchQueryRequest {
    private final String activityId;
    private final String contentTier;
    private final InstantEntitySpecification entitySpec;
    private final String query;
    private final String queryId;
    private final InstantTextSpecification textSpec;

    private InstantSearchQueryRequest(String str, String str2, String str3, String str4, InstantTextSpecification instantTextSpecification, InstantEntitySpecification instantEntitySpecification) {
        this.query = str;
        this.activityId = str2;
        this.queryId = str3;
        this.contentTier = str4;
        this.textSpec = instantTextSpecification;
        this.entitySpec = instantEntitySpecification;
    }

    public String getQuery() {
        return this.query;
    }

    public String getActivityId() {
        return this.activityId;
    }

    public String getQueryId() {
        return this.queryId;
    }

    public String getContentTier() {
        return this.contentTier;
    }

    public InstantTextSpecification getTextSpec() {
        return this.textSpec;
    }

    public InstantEntitySpecification getEntitySpec() {
        return this.entitySpec;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String activityId;
        private String contentTier;
        private InstantEntitySpecification entitySpec;
        private String query;
        private String queryId;
        private InstantTextSpecification textSpec;

        public Builder withQuery(String str) {
            this.query = str;
            return this;
        }

        public Builder withActivityId(String str) {
            this.activityId = str;
            return this;
        }

        public Builder withQueryId(String str) {
            this.queryId = str;
            return this;
        }

        public Builder withContentTier(String str) {
            this.contentTier = str;
            return this;
        }

        public Builder withTextSpec(InstantTextSpecification instantTextSpecification) {
            this.textSpec = instantTextSpecification;
            return this;
        }

        public Builder withEntitySpec(InstantEntitySpecification instantEntitySpecification) {
            this.entitySpec = instantEntitySpecification;
            return this;
        }

        public InstantSearchQueryRequest build() {
            return new InstantSearchQueryRequest(this.query, this.activityId, this.queryId, this.contentTier, this.textSpec, this.entitySpec);
        }
    }
}
