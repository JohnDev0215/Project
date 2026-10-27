package com.amazon.music.find.model.configuration;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchSuggestionConfiguration.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/find/model/configuration/SearchSuggestionConfiguration;", "", "instantSearchConfig", "Lcom/amazon/music/find/model/configuration/InstantSearchSuggestionConfig;", "maxSearchSuggestionCount", "", "debounceDurationInMilliseconds", "", "(Lcom/amazon/music/find/model/configuration/InstantSearchSuggestionConfig;IJ)V", "getDebounceDurationInMilliseconds", "()J", "getInstantSearchConfig", "()Lcom/amazon/music/find/model/configuration/InstantSearchSuggestionConfig;", "getMaxSearchSuggestionCount", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SearchSuggestionConfiguration {
    private final long debounceDurationInMilliseconds;
    private final InstantSearchSuggestionConfig instantSearchConfig;
    private final int maxSearchSuggestionCount;

    public static /* synthetic */ SearchSuggestionConfiguration copy$default(SearchSuggestionConfiguration searchSuggestionConfiguration, InstantSearchSuggestionConfig instantSearchSuggestionConfig, int i, long j, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            instantSearchSuggestionConfig = searchSuggestionConfiguration.instantSearchConfig;
        }
        if ((i2 & 2) != 0) {
            i = searchSuggestionConfiguration.maxSearchSuggestionCount;
        }
        if ((i2 & 4) != 0) {
            j = searchSuggestionConfiguration.debounceDurationInMilliseconds;
        }
        return searchSuggestionConfiguration.copy(instantSearchSuggestionConfig, i, j);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final InstantSearchSuggestionConfig getInstantSearchConfig() {
        return this.instantSearchConfig;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getMaxSearchSuggestionCount() {
        return this.maxSearchSuggestionCount;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getDebounceDurationInMilliseconds() {
        return this.debounceDurationInMilliseconds;
    }

    public final SearchSuggestionConfiguration copy(InstantSearchSuggestionConfig instantSearchConfig, int maxSearchSuggestionCount, long debounceDurationInMilliseconds) {
        Intrinsics.checkNotNullParameter(instantSearchConfig, "instantSearchConfig");
        return new SearchSuggestionConfiguration(instantSearchConfig, maxSearchSuggestionCount, debounceDurationInMilliseconds);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchSuggestionConfiguration)) {
            return false;
        }
        SearchSuggestionConfiguration searchSuggestionConfiguration = (SearchSuggestionConfiguration) other;
        return Intrinsics.areEqual(this.instantSearchConfig, searchSuggestionConfiguration.instantSearchConfig) && this.maxSearchSuggestionCount == searchSuggestionConfiguration.maxSearchSuggestionCount && this.debounceDurationInMilliseconds == searchSuggestionConfiguration.debounceDurationInMilliseconds;
    }

    public int hashCode() {
        return (((this.instantSearchConfig.hashCode() * 31) + Integer.hashCode(this.maxSearchSuggestionCount)) * 31) + Long.hashCode(this.debounceDurationInMilliseconds);
    }

    public String toString() {
        return "SearchSuggestionConfiguration(instantSearchConfig=" + this.instantSearchConfig + ", maxSearchSuggestionCount=" + this.maxSearchSuggestionCount + ", debounceDurationInMilliseconds=" + this.debounceDurationInMilliseconds + ')';
    }

    public SearchSuggestionConfiguration(InstantSearchSuggestionConfig instantSearchConfig, int i, long j) {
        Intrinsics.checkNotNullParameter(instantSearchConfig, "instantSearchConfig");
        this.instantSearchConfig = instantSearchConfig;
        this.maxSearchSuggestionCount = i;
        this.debounceDurationInMilliseconds = j;
    }

    public final InstantSearchSuggestionConfig getInstantSearchConfig() {
        return this.instantSearchConfig;
    }

    public final int getMaxSearchSuggestionCount() {
        return this.maxSearchSuggestionCount;
    }

    public final long getDebounceDurationInMilliseconds() {
        return this.debounceDurationInMilliseconds;
    }
}
