package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: CuePoints.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\r\u001a\u00020\u0004¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\f¨\u0006\u0011"}, d2 = {"Lcom/amazon/digitalmusicxp/types/CuePoints;", "", "", "toString", "", "hashCode", "other", "", "equals", "startOffsetInMs", "I", "getStartOffsetInMs", "()I", "endOffsetInMs", "getEndOffsetInMs", "<init>", "(II)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class CuePoints {
    private final int endOffsetInMs;
    private final int startOffsetInMs;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CuePoints)) {
            return false;
        }
        CuePoints cuePoints = (CuePoints) other;
        return this.startOffsetInMs == cuePoints.startOffsetInMs && this.endOffsetInMs == cuePoints.endOffsetInMs;
    }

    public int hashCode() {
        return (Integer.hashCode(this.startOffsetInMs) * 31) + Integer.hashCode(this.endOffsetInMs);
    }

    public String toString() {
        return "CuePoints(startOffsetInMs=" + this.startOffsetInMs + ", endOffsetInMs=" + this.endOffsetInMs + ')';
    }

    public CuePoints(int i, int i2) {
        this.startOffsetInMs = i;
        this.endOffsetInMs = i2;
        ExpectedFunctionsKt.freeze(this);
    }

    public final int getEndOffsetInMs() {
        return this.endOffsetInMs;
    }

    public final int getStartOffsetInMs() {
        return this.startOffsetInMs;
    }
}
