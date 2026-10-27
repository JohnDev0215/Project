package com.amazon.mp3.catalog.fragment.viewmodel;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* JADX INFO: compiled from: PageViewModelProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\n\u001a\u00020\t\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/PageFetchInfo;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/mp3/catalog/fragment/viewmodel/FetchType;", "fetchType", "Lcom/amazon/mp3/catalog/fragment/viewmodel/FetchType;", "getFetchType", "()Lcom/amazon/mp3/catalog/fragment/viewmodel/FetchType;", "Lkotlin/ranges/IntRange;", "indicesChanged", "Lkotlin/ranges/IntRange;", "getIndicesChanged", "()Lkotlin/ranges/IntRange;", "<init>", "(Lcom/amazon/mp3/catalog/fragment/viewmodel/FetchType;Lkotlin/ranges/IntRange;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class PageFetchInfo {
    private final FetchType fetchType;
    private final IntRange indicesChanged;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PageFetchInfo)) {
            return false;
        }
        PageFetchInfo pageFetchInfo = (PageFetchInfo) other;
        return this.fetchType == pageFetchInfo.fetchType && Intrinsics.areEqual(this.indicesChanged, pageFetchInfo.indicesChanged);
    }

    public int hashCode() {
        int iHashCode = this.fetchType.hashCode() * 31;
        IntRange intRange = this.indicesChanged;
        return iHashCode + (intRange == null ? 0 : intRange.hashCode());
    }

    public String toString() {
        return "PageFetchInfo(fetchType=" + this.fetchType + ", indicesChanged=" + this.indicesChanged + ')';
    }

    public PageFetchInfo(FetchType fetchType, IntRange intRange) {
        Intrinsics.checkNotNullParameter(fetchType, "fetchType");
        this.fetchType = fetchType;
        this.indicesChanged = intRange;
    }

    public /* synthetic */ PageFetchInfo(FetchType fetchType, IntRange intRange, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fetchType, (i & 2) != 0 ? null : intRange);
    }

    public final IntRange getIndicesChanged() {
        return this.indicesChanged;
    }
}
