package com.amazon.music.platform.metrics.event;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LatencyWrapper.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\f¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/platform/metrics/event/LatencyWrapper;", "", "", "toString", "", "hashCode", "other", "", "equals", "latencyTrackingLeg", "Ljava/lang/String;", "getLatencyTrackingLeg", "()Ljava/lang/String;", "pageLoadLatencyCode", "getPageLoadLatencyCode", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class LatencyWrapper {
    private final String latencyTrackingLeg;
    private final String pageLoadLatencyCode;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LatencyWrapper)) {
            return false;
        }
        LatencyWrapper latencyWrapper = (LatencyWrapper) other;
        return Intrinsics.areEqual(this.latencyTrackingLeg, latencyWrapper.latencyTrackingLeg) && Intrinsics.areEqual(this.pageLoadLatencyCode, latencyWrapper.pageLoadLatencyCode);
    }

    public int hashCode() {
        return (this.latencyTrackingLeg.hashCode() * 31) + this.pageLoadLatencyCode.hashCode();
    }

    public String toString() {
        return "LatencyWrapper(latencyTrackingLeg=" + this.latencyTrackingLeg + ", pageLoadLatencyCode=" + this.pageLoadLatencyCode + ')';
    }

    public LatencyWrapper(String latencyTrackingLeg, String pageLoadLatencyCode) {
        Intrinsics.checkNotNullParameter(latencyTrackingLeg, "latencyTrackingLeg");
        Intrinsics.checkNotNullParameter(pageLoadLatencyCode, "pageLoadLatencyCode");
        this.latencyTrackingLeg = latencyTrackingLeg;
        this.pageLoadLatencyCode = pageLoadLatencyCode;
    }

    public final String getLatencyTrackingLeg() {
        return this.latencyTrackingLeg;
    }

    public final String getPageLoadLatencyCode() {
        return this.pageLoadLatencyCode;
    }
}
