package com.amazon.music.metrics.mts.event.definition.latency;

import com.amazon.music.skyfire.models.TetheredMessageKey;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LatencyTrackingEvent.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/metrics/mts/event/definition/latency/LatencyTrackingEvent;", "", "", "toString", "", "hashCode", "other", "", "equals", "", TetheredMessageKey.timestamp, "J", "getTimestamp", "()J", "Lcom/amazon/music/metrics/mts/event/definition/latency/LatencyEventDescriptor;", "latencyEventDescriptor", "Lcom/amazon/music/metrics/mts/event/definition/latency/LatencyEventDescriptor;", "getLatencyEventDescriptor", "()Lcom/amazon/music/metrics/mts/event/definition/latency/LatencyEventDescriptor;", "<init>", "(JLcom/amazon/music/metrics/mts/event/definition/latency/LatencyEventDescriptor;)V", "DMMMetricsMTSEvents_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class LatencyTrackingEvent {
    private final LatencyEventDescriptor latencyEventDescriptor;
    private final long timestamp;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LatencyTrackingEvent)) {
            return false;
        }
        LatencyTrackingEvent latencyTrackingEvent = (LatencyTrackingEvent) other;
        return this.timestamp == latencyTrackingEvent.timestamp && Intrinsics.areEqual(this.latencyEventDescriptor, latencyTrackingEvent.latencyEventDescriptor);
    }

    public int hashCode() {
        return (Long.hashCode(this.timestamp) * 31) + this.latencyEventDescriptor.hashCode();
    }

    public String toString() {
        return "LatencyTrackingEvent(timestamp=" + this.timestamp + ", latencyEventDescriptor=" + this.latencyEventDescriptor + ')';
    }

    public LatencyTrackingEvent(long j, LatencyEventDescriptor latencyEventDescriptor) {
        Intrinsics.checkNotNullParameter(latencyEventDescriptor, "latencyEventDescriptor");
        this.timestamp = j;
        this.latencyEventDescriptor = latencyEventDescriptor;
    }

    public final LatencyEventDescriptor getLatencyEventDescriptor() {
        return this.latencyEventDescriptor;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }
}
