package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class SearchSeedContextInput {
    private final MetricsContextInput metricsContext;
    private final String pageToken;
    private final String searchTerm;
    private final SearchTypeEnum searchType;

    public interface BuildStep {
    }

    public static class Builder implements SearchTypeStep, SearchTermStep, MetricsContextStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface MetricsContextStep {
    }

    public interface SearchTermStep {
    }

    public interface SearchTypeStep {
    }

    public SearchTypeEnum getSearchType() {
        return this.searchType;
    }

    public String getSearchTerm() {
        return this.searchTerm;
    }

    public String getPageToken() {
        return this.pageToken;
    }

    public MetricsContextInput getMetricsContext() {
        return this.metricsContext;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SearchSeedContextInput.class != obj.getClass()) {
            return false;
        }
        SearchSeedContextInput searchSeedContextInput = (SearchSeedContextInput) obj;
        return ObjectsCompat.equals(getSearchType(), searchSeedContextInput.getSearchType()) && ObjectsCompat.equals(getSearchTerm(), searchSeedContextInput.getSearchTerm()) && ObjectsCompat.equals(getPageToken(), searchSeedContextInput.getPageToken()) && ObjectsCompat.equals(getMetricsContext(), searchSeedContextInput.getMetricsContext());
    }

    public int hashCode() {
        return (getSearchType() + getSearchTerm() + getPageToken() + getMetricsContext()).hashCode();
    }
}
