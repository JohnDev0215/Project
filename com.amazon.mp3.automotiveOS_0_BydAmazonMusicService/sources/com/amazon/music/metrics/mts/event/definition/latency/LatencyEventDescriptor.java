package com.amazon.music.metrics.mts.event.definition.latency;

import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LatencyEventDescriptor.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/metrics/mts/event/definition/latency/LatencyEventDescriptor;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/LatencyTrackingLeg;", "latencyLeg", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/LatencyTrackingLeg;", "getLatencyLeg", "()Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/LatencyTrackingLeg;", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "pageLoadLatencyCode", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "getPageLoadLatencyCode", "()Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "<init>", "(Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/LatencyTrackingLeg;Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;)V", "DMMMetricsMTSEvents_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class LatencyEventDescriptor {
    private final LatencyTrackingLeg latencyLeg;
    private final PageLoadLatencyCode pageLoadLatencyCode;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LatencyEventDescriptor)) {
            return false;
        }
        LatencyEventDescriptor latencyEventDescriptor = (LatencyEventDescriptor) other;
        return this.latencyLeg == latencyEventDescriptor.latencyLeg && this.pageLoadLatencyCode == latencyEventDescriptor.pageLoadLatencyCode;
    }

    public int hashCode() {
        return (this.latencyLeg.hashCode() * 31) + this.pageLoadLatencyCode.hashCode();
    }

    public String toString() {
        return "LatencyEventDescriptor(latencyLeg=" + this.latencyLeg + ", pageLoadLatencyCode=" + this.pageLoadLatencyCode + ')';
    }

    public LatencyEventDescriptor(LatencyTrackingLeg latencyLeg, PageLoadLatencyCode pageLoadLatencyCode) {
        Intrinsics.checkNotNullParameter(latencyLeg, "latencyLeg");
        Intrinsics.checkNotNullParameter(pageLoadLatencyCode, "pageLoadLatencyCode");
        this.latencyLeg = latencyLeg;
        this.pageLoadLatencyCode = pageLoadLatencyCode;
    }

    public final LatencyTrackingLeg getLatencyLeg() {
        return this.latencyLeg;
    }

    public final PageLoadLatencyCode getPageLoadLatencyCode() {
        return this.pageLoadLatencyCode;
    }
}
