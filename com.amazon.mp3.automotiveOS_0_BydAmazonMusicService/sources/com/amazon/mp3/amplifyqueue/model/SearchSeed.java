package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class SearchSeed {
    private final String pageToken;
    private final String searchTerm;
    private final SearchTypeEnum searchType;

    public interface BuildStep {
    }

    public static class Builder implements SearchTypeStep, SearchTermStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SearchSeed.class != obj.getClass()) {
            return false;
        }
        SearchSeed searchSeed = (SearchSeed) obj;
        return ObjectsCompat.equals(getSearchType(), searchSeed.getSearchType()) && ObjectsCompat.equals(getSearchTerm(), searchSeed.getSearchTerm()) && ObjectsCompat.equals(getPageToken(), searchSeed.getPageToken());
    }

    public int hashCode() {
        return (getSearchType() + getSearchTerm() + getPageToken()).hashCode();
    }
}
