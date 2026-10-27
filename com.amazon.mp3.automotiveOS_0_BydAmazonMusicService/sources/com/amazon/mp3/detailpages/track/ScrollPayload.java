package com.amazon.mp3.detailpages.track;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScrollPayload.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\r\u001a\u00020\u0004\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/detailpages/track/ScrollPayload;", "", "", "toString", "", "hashCode", "other", "", "equals", "firstVisibleIndex", "I", "getFirstVisibleIndex", "()I", "lastVisibleIndex", "getLastVisibleIndex", "Lcom/amazon/mp3/detailpages/track/ScrollDirection;", "scrollDirection", "Lcom/amazon/mp3/detailpages/track/ScrollDirection;", "getScrollDirection", "()Lcom/amazon/mp3/detailpages/track/ScrollDirection;", "<init>", "(IILcom/amazon/mp3/detailpages/track/ScrollDirection;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class ScrollPayload {
    private final int firstVisibleIndex;
    private final int lastVisibleIndex;
    private final ScrollDirection scrollDirection;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ScrollPayload)) {
            return false;
        }
        ScrollPayload scrollPayload = (ScrollPayload) other;
        return this.firstVisibleIndex == scrollPayload.firstVisibleIndex && this.lastVisibleIndex == scrollPayload.lastVisibleIndex && this.scrollDirection == scrollPayload.scrollDirection;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.firstVisibleIndex) * 31) + Integer.hashCode(this.lastVisibleIndex)) * 31) + this.scrollDirection.hashCode();
    }

    public String toString() {
        return "ScrollPayload(firstVisibleIndex=" + this.firstVisibleIndex + ", lastVisibleIndex=" + this.lastVisibleIndex + ", scrollDirection=" + this.scrollDirection + ')';
    }

    public ScrollPayload(int i, int i2, ScrollDirection scrollDirection) {
        Intrinsics.checkNotNullParameter(scrollDirection, "scrollDirection");
        this.firstVisibleIndex = i;
        this.lastVisibleIndex = i2;
        this.scrollDirection = scrollDirection;
    }

    public final int getFirstVisibleIndex() {
        return this.firstVisibleIndex;
    }

    public final int getLastVisibleIndex() {
        return this.lastVisibleIndex;
    }

    public final ScrollDirection getScrollDirection() {
        return this.scrollDirection;
    }
}
