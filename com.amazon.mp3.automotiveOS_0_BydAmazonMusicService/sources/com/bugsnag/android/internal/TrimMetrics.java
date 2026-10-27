package com.bugsnag.android.internal;

import kotlin.Metadata;

/* JADX INFO: compiled from: InternalMetrics.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\b\u0080\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\u0003\u001a\u00020\u0002HÆ\u0003J\t\u0010\u0004\u001a\u00020\u0002HÆ\u0003J\t\u0010\u0006\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0007\u001a\u00020\u0002HÖ\u0001J\u0013\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\f\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u0013"}, d2 = {"Lcom/bugsnag/android/internal/TrimMetrics;", "", "", "component1", "component2", "", "toString", "hashCode", "other", "", "equals", "itemsTrimmed", "I", "getItemsTrimmed", "()I", "dataTrimmed", "getDataTrimmed", "<init>", "(II)V", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final /* data */ class TrimMetrics {
    private final int dataTrimmed;
    private final int itemsTrimmed;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getItemsTrimmed() {
        return this.itemsTrimmed;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getDataTrimmed() {
        return this.dataTrimmed;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TrimMetrics)) {
            return false;
        }
        TrimMetrics trimMetrics = (TrimMetrics) other;
        return this.itemsTrimmed == trimMetrics.itemsTrimmed && this.dataTrimmed == trimMetrics.dataTrimmed;
    }

    public int hashCode() {
        return (this.itemsTrimmed * 31) + this.dataTrimmed;
    }

    public String toString() {
        return "TrimMetrics(itemsTrimmed=" + this.itemsTrimmed + ", dataTrimmed=" + this.dataTrimmed + ")";
    }

    public TrimMetrics(int i, int i2) {
        this.itemsTrimmed = i;
        this.dataTrimmed = i2;
    }

    public final int getItemsTrimmed() {
        return this.itemsTrimmed;
    }

    public final int getDataTrimmed() {
        return this.dataTrimmed;
    }
}
