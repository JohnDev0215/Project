package com.amazon.music.find.viewmodels;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PageDataShouldBeUpdatedEvent.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B!\u0012\b\u0010\u0003\u001a\u0004\u0018\u00018\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u0010\u001a\u0004\u0018\u00018\u0000HÆ\u0003¢\u0006\u0002\u0010\u000eJ\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000bJ6\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00018\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\tR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\u0003\u001a\u0004\u0018\u00018\u0000¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/find/viewmodels/PageDataShouldBeUpdatedEvent;", "T", "", "pageData", "isFetching", "", "lastFetchRequestedAt", "", "(Ljava/lang/Object;ZLjava/lang/Long;)V", "()Z", "getLastFetchRequestedAt", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getPageData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "component2", "component3", "copy", "(Ljava/lang/Object;ZLjava/lang/Long;)Lcom/amazon/music/find/viewmodels/PageDataShouldBeUpdatedEvent;", "equals", "other", "hashCode", "", "toString", "", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class PageDataShouldBeUpdatedEvent<T> {
    private final boolean isFetching;
    private final Long lastFetchRequestedAt;
    private final T pageData;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PageDataShouldBeUpdatedEvent copy$default(PageDataShouldBeUpdatedEvent pageDataShouldBeUpdatedEvent, Object obj, boolean z, Long l, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = pageDataShouldBeUpdatedEvent.pageData;
        }
        if ((i & 2) != 0) {
            z = pageDataShouldBeUpdatedEvent.isFetching;
        }
        if ((i & 4) != 0) {
            l = pageDataShouldBeUpdatedEvent.lastFetchRequestedAt;
        }
        return pageDataShouldBeUpdatedEvent.copy(obj, z, l);
    }

    public final T component1() {
        return this.pageData;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsFetching() {
        return this.isFetching;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Long getLastFetchRequestedAt() {
        return this.lastFetchRequestedAt;
    }

    public final PageDataShouldBeUpdatedEvent<T> copy(T pageData, boolean isFetching, Long lastFetchRequestedAt) {
        return new PageDataShouldBeUpdatedEvent<>(pageData, isFetching, lastFetchRequestedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PageDataShouldBeUpdatedEvent)) {
            return false;
        }
        PageDataShouldBeUpdatedEvent pageDataShouldBeUpdatedEvent = (PageDataShouldBeUpdatedEvent) other;
        return Intrinsics.areEqual(this.pageData, pageDataShouldBeUpdatedEvent.pageData) && this.isFetching == pageDataShouldBeUpdatedEvent.isFetching && Intrinsics.areEqual(this.lastFetchRequestedAt, pageDataShouldBeUpdatedEvent.lastFetchRequestedAt);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4 */
    public int hashCode() {
        T t = this.pageData;
        int iHashCode = (t == null ? 0 : t.hashCode()) * 31;
        boolean z = this.isFetching;
        ?? r2 = z;
        if (z) {
            r2 = 1;
        }
        int i = (iHashCode + r2) * 31;
        Long l = this.lastFetchRequestedAt;
        return i + (l != null ? l.hashCode() : 0);
    }

    public String toString() {
        return "PageDataShouldBeUpdatedEvent(pageData=" + this.pageData + ", isFetching=" + this.isFetching + ", lastFetchRequestedAt=" + this.lastFetchRequestedAt + ')';
    }

    public PageDataShouldBeUpdatedEvent(T t, boolean z, Long l) {
        this.pageData = t;
        this.isFetching = z;
        this.lastFetchRequestedAt = l;
    }

    public final T getPageData() {
        return this.pageData;
    }

    public final boolean isFetching() {
        return this.isFetching;
    }

    public final Long getLastFetchRequestedAt() {
        return this.lastFetchRequestedAt;
    }
}
