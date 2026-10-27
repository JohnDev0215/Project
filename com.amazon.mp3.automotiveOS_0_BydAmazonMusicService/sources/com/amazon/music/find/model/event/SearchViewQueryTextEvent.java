package com.amazon.music.find.model.event;

import com.google.android.gms.actions.SearchIntents;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchViewQueryTextEvent.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\r\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/find/model/event/SearchViewQueryTextEvent;", "", SearchIntents.EXTRA_QUERY, "", "isSubmit", "", "(Ljava/lang/String;Z)V", "()Z", "getQuery", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SearchViewQueryTextEvent {
    private final boolean isSubmit;
    private final String query;

    public static /* synthetic */ SearchViewQueryTextEvent copy$default(SearchViewQueryTextEvent searchViewQueryTextEvent, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = searchViewQueryTextEvent.query;
        }
        if ((i & 2) != 0) {
            z = searchViewQueryTextEvent.isSubmit;
        }
        return searchViewQueryTextEvent.copy(str, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getQuery() {
        return this.query;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsSubmit() {
        return this.isSubmit;
    }

    public final SearchViewQueryTextEvent copy(String query, boolean isSubmit) {
        Intrinsics.checkNotNullParameter(query, "query");
        return new SearchViewQueryTextEvent(query, isSubmit);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchViewQueryTextEvent)) {
            return false;
        }
        SearchViewQueryTextEvent searchViewQueryTextEvent = (SearchViewQueryTextEvent) other;
        return Intrinsics.areEqual(this.query, searchViewQueryTextEvent.query) && this.isSubmit == searchViewQueryTextEvent.isSubmit;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    public int hashCode() {
        int iHashCode = this.query.hashCode() * 31;
        boolean z = this.isSubmit;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode + r1;
    }

    public String toString() {
        return "SearchViewQueryTextEvent(query=" + this.query + ", isSubmit=" + this.isSubmit + ')';
    }

    public SearchViewQueryTextEvent(String query, boolean z) {
        Intrinsics.checkNotNullParameter(query, "query");
        this.query = query;
        this.isSubmit = z;
    }

    public final String getQuery() {
        return this.query;
    }

    public final boolean isSubmit() {
        return this.isSubmit;
    }
}
