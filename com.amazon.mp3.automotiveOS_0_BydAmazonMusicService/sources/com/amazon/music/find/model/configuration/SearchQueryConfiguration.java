package com.amazon.music.find.model.configuration;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchQueryConfiguration.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ$\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\t¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/find/model/configuration/SearchQueryConfiguration;", "", "count", "", "ttlDays", "(ILjava/lang/Integer;)V", "getCount", "()I", "getTtlDays", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "copy", "(ILjava/lang/Integer;)Lcom/amazon/music/find/model/configuration/SearchQueryConfiguration;", "equals", "", "other", "hashCode", "toString", "", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SearchQueryConfiguration {
    private final int count;
    private final Integer ttlDays;

    public static /* synthetic */ SearchQueryConfiguration copy$default(SearchQueryConfiguration searchQueryConfiguration, int i, Integer num, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = searchQueryConfiguration.count;
        }
        if ((i2 & 2) != 0) {
            num = searchQueryConfiguration.ttlDays;
        }
        return searchQueryConfiguration.copy(i, num);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getCount() {
        return this.count;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getTtlDays() {
        return this.ttlDays;
    }

    public final SearchQueryConfiguration copy(int count, Integer ttlDays) {
        return new SearchQueryConfiguration(count, ttlDays);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchQueryConfiguration)) {
            return false;
        }
        SearchQueryConfiguration searchQueryConfiguration = (SearchQueryConfiguration) other;
        return this.count == searchQueryConfiguration.count && Intrinsics.areEqual(this.ttlDays, searchQueryConfiguration.ttlDays);
    }

    public int hashCode() {
        int iHashCode = Integer.hashCode(this.count) * 31;
        Integer num = this.ttlDays;
        return iHashCode + (num == null ? 0 : num.hashCode());
    }

    public String toString() {
        return "SearchQueryConfiguration(count=" + this.count + ", ttlDays=" + this.ttlDays + ')';
    }

    public SearchQueryConfiguration(int i, Integer num) {
        this.count = i;
        this.ttlDays = num;
    }

    public final int getCount() {
        return this.count;
    }

    public final Integer getTtlDays() {
        return this.ttlDays;
    }
}
