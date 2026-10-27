package com.amazon.mp3.library.refinement.query;

import com.amazon.mp3.library.refinement.Refinable;
import kotlin.Metadata;

/* JADX INFO: compiled from: ListQuery.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\b\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003B\u0017\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\u000f\u001a\u00020\u0006¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0007\u001a\u00020\u0006HÖ\u0001J\u0013\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0003HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u000f\u0010\f\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/library/refinement/query/ListQuery;", "Lcom/amazon/mp3/library/refinement/Refinable;", "R", "", "", "toString", "", "hashCode", "other", "", "equals", "pageIndex", "I", "getPageIndex", "()I", "pageSize", "getPageSize", "<init>", "(II)V", "MusicExperienceCore_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class ListQuery<R extends Refinable> {
    private final int pageIndex;
    private final int pageSize;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ListQuery)) {
            return false;
        }
        ListQuery listQuery = (ListQuery) other;
        return this.pageIndex == listQuery.pageIndex && this.pageSize == listQuery.pageSize;
    }

    public int hashCode() {
        return (Integer.hashCode(this.pageIndex) * 31) + Integer.hashCode(this.pageSize);
    }

    public String toString() {
        return "ListQuery(pageIndex=" + this.pageIndex + ", pageSize=" + this.pageSize + ')';
    }

    public ListQuery(int i, int i2) {
        this.pageIndex = i;
        this.pageSize = i2;
    }

    public final int getPageIndex() {
        return this.pageIndex;
    }

    public final int getPageSize() {
        return this.pageSize;
    }
}
