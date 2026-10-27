package com.amplifyframework.geo.options;

import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes5.dex */
public class GeoSearchByCoordinatesOptions {
    private final int maxResults;

    protected GeoSearchByCoordinatesOptions(Builder builder) {
        this.maxResults = builder.maxResults;
    }

    public int getMaxResults() {
        return this.maxResults;
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    @NonNull
    public static GeoSearchByCoordinatesOptions defaults() {
        return builder().build();
    }

    public static class Builder {
        private static final int DEFAULT_MAX_RESULTS_LIMIT = 50;
        private int maxResults = 50;

        @NonNull
        public Builder maxResults(int i) {
            this.maxResults = i;
            return this;
        }

        @NonNull
        public GeoSearchByCoordinatesOptions build() {
            return new GeoSearchByCoordinatesOptions(this);
        }
    }
}
