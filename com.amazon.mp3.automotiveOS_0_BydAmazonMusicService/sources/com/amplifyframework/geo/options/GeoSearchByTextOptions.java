package com.amplifyframework.geo.options;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.geo.models.CountryCode;
import com.amplifyframework.geo.models.SearchArea;
import com.amplifyframework.util.Immutable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public class GeoSearchByTextOptions {
    private final List<CountryCode> countries;
    private final int maxResults;
    private final SearchArea searchArea;

    protected GeoSearchByTextOptions(Builder builder) {
        this.maxResults = builder.maxResults;
        this.searchArea = builder.searchArea;
        this.countries = builder.countries;
    }

    public int getMaxResults() {
        return this.maxResults;
    }

    @Nullable
    public SearchArea getSearchArea() {
        return this.searchArea;
    }

    @NonNull
    public List<CountryCode> getCountries() {
        return Immutable.of(this.countries);
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    @NonNull
    public static GeoSearchByTextOptions defaults() {
        return builder().build();
    }

    public static class Builder {
        private static final int DEFAULT_MAX_RESULTS_LIMIT = 50;
        private SearchArea searchArea;
        private int maxResults = 50;
        private List<CountryCode> countries = Collections.singletonList(CountryCode.USA);

        protected Builder() {
        }

        @NonNull
        public Builder maxResults(int i) {
            this.maxResults = i;
            return this;
        }

        @NonNull
        public Builder searchArea(@NonNull SearchArea searchArea) {
            Objects.requireNonNull(searchArea);
            this.searchArea = searchArea;
            return this;
        }

        @NonNull
        public Builder countries(@NonNull List<CountryCode> list) {
            Objects.requireNonNull(list);
            this.countries = list;
            if (list.isEmpty()) {
                throw new IllegalArgumentException("Country filter cannot be empty.");
            }
            return this;
        }

        @NonNull
        public GeoSearchByTextOptions build() {
            return new GeoSearchByTextOptions(this);
        }
    }
}
