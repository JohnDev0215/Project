package com.amazon.music.find.model.configuration;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchHistoryConfiguration.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J+\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/find/model/configuration/SearchHistoryConfiguration;", "", "searchEntityConfiguration", "Lcom/amazon/music/find/model/configuration/SearchEntityConfiguration;", "searchQueryConfiguration", "Lcom/amazon/music/find/model/configuration/SearchQueryConfiguration;", "maxReturnEntityCount", "", "(Lcom/amazon/music/find/model/configuration/SearchEntityConfiguration;Lcom/amazon/music/find/model/configuration/SearchQueryConfiguration;I)V", "getMaxReturnEntityCount", "()I", "getSearchEntityConfiguration", "()Lcom/amazon/music/find/model/configuration/SearchEntityConfiguration;", "getSearchQueryConfiguration", "()Lcom/amazon/music/find/model/configuration/SearchQueryConfiguration;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SearchHistoryConfiguration {
    private final int maxReturnEntityCount;
    private final SearchEntityConfiguration searchEntityConfiguration;
    private final SearchQueryConfiguration searchQueryConfiguration;

    public static /* synthetic */ SearchHistoryConfiguration copy$default(SearchHistoryConfiguration searchHistoryConfiguration, SearchEntityConfiguration searchEntityConfiguration, SearchQueryConfiguration searchQueryConfiguration, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            searchEntityConfiguration = searchHistoryConfiguration.searchEntityConfiguration;
        }
        if ((i2 & 2) != 0) {
            searchQueryConfiguration = searchHistoryConfiguration.searchQueryConfiguration;
        }
        if ((i2 & 4) != 0) {
            i = searchHistoryConfiguration.maxReturnEntityCount;
        }
        return searchHistoryConfiguration.copy(searchEntityConfiguration, searchQueryConfiguration, i);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final SearchEntityConfiguration getSearchEntityConfiguration() {
        return this.searchEntityConfiguration;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final SearchQueryConfiguration getSearchQueryConfiguration() {
        return this.searchQueryConfiguration;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getMaxReturnEntityCount() {
        return this.maxReturnEntityCount;
    }

    public final SearchHistoryConfiguration copy(SearchEntityConfiguration searchEntityConfiguration, SearchQueryConfiguration searchQueryConfiguration, int maxReturnEntityCount) {
        return new SearchHistoryConfiguration(searchEntityConfiguration, searchQueryConfiguration, maxReturnEntityCount);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchHistoryConfiguration)) {
            return false;
        }
        SearchHistoryConfiguration searchHistoryConfiguration = (SearchHistoryConfiguration) other;
        return Intrinsics.areEqual(this.searchEntityConfiguration, searchHistoryConfiguration.searchEntityConfiguration) && Intrinsics.areEqual(this.searchQueryConfiguration, searchHistoryConfiguration.searchQueryConfiguration) && this.maxReturnEntityCount == searchHistoryConfiguration.maxReturnEntityCount;
    }

    public int hashCode() {
        SearchEntityConfiguration searchEntityConfiguration = this.searchEntityConfiguration;
        int iHashCode = (searchEntityConfiguration == null ? 0 : searchEntityConfiguration.hashCode()) * 31;
        SearchQueryConfiguration searchQueryConfiguration = this.searchQueryConfiguration;
        return ((iHashCode + (searchQueryConfiguration != null ? searchQueryConfiguration.hashCode() : 0)) * 31) + Integer.hashCode(this.maxReturnEntityCount);
    }

    public String toString() {
        return "SearchHistoryConfiguration(searchEntityConfiguration=" + this.searchEntityConfiguration + ", searchQueryConfiguration=" + this.searchQueryConfiguration + ", maxReturnEntityCount=" + this.maxReturnEntityCount + ')';
    }

    public SearchHistoryConfiguration(SearchEntityConfiguration searchEntityConfiguration, SearchQueryConfiguration searchQueryConfiguration, int i) {
        this.searchEntityConfiguration = searchEntityConfiguration;
        this.searchQueryConfiguration = searchQueryConfiguration;
        this.maxReturnEntityCount = i;
    }

    public final SearchEntityConfiguration getSearchEntityConfiguration() {
        return this.searchEntityConfiguration;
    }

    public final SearchQueryConfiguration getSearchQueryConfiguration() {
        return this.searchQueryConfiguration;
    }

    public final int getMaxReturnEntityCount() {
        return this.maxReturnEntityCount;
    }
}
