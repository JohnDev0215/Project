package com.amazon.music.metrics.mts.event.definition.latency;

import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LatencyDuration.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000e\u001a\u00020\t\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0019\u0010\u001aJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\rR\u0017\u0010\u0011\u001a\u00020\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0018\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\r¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/metrics/mts/event/definition/latency/LatencyDuration;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "startTime", "J", "getStartTime", "()J", "endTime", "getEndTime", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/LatencyTrackingLeg;", "latencyLeg", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/LatencyTrackingLeg;", "getLatencyLeg", "()Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/LatencyTrackingLeg;", "explicitDuration", "Ljava/lang/Long;", "getDuration", "duration", "<init>", "(JJLcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/LatencyTrackingLeg;Ljava/lang/Long;)V", "DMMMetricsMTSEvents_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class LatencyDuration {
    private final long endTime;
    private final Long explicitDuration;
    private final LatencyTrackingLeg latencyLeg;
    private final long startTime;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LatencyDuration)) {
            return false;
        }
        LatencyDuration latencyDuration = (LatencyDuration) other;
        return this.startTime == latencyDuration.startTime && this.endTime == latencyDuration.endTime && this.latencyLeg == latencyDuration.latencyLeg && Intrinsics.areEqual(this.explicitDuration, latencyDuration.explicitDuration);
    }

    public int hashCode() {
        int iHashCode = ((((Long.hashCode(this.startTime) * 31) + Long.hashCode(this.endTime)) * 31) + this.latencyLeg.hashCode()) * 31;
        Long l = this.explicitDuration;
        return iHashCode + (l == null ? 0 : l.hashCode());
    }

    public String toString() {
        return "LatencyDuration(startTime=" + this.startTime + ", endTime=" + this.endTime + ", latencyLeg=" + this.latencyLeg + ", explicitDuration=" + this.explicitDuration + ')';
    }

    public LatencyDuration(long j, long j2, LatencyTrackingLeg latencyLeg, Long l) {
        Intrinsics.checkNotNullParameter(latencyLeg, "latencyLeg");
        this.startTime = j;
        this.endTime = j2;
        this.latencyLeg = latencyLeg;
        this.explicitDuration = l;
    }

    public /* synthetic */ LatencyDuration(long j, long j2, LatencyTrackingLeg latencyTrackingLeg, Long l, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0L : j, (i & 2) != 0 ? 0L : j2, latencyTrackingLeg, (i & 8) != 0 ? null : l);
    }

    public final LatencyTrackingLeg getLatencyLeg() {
        return this.latencyLeg;
    }

    public final long getDuration() {
        Long l = this.explicitDuration;
        return l == null ? this.endTime - this.startTime : l.longValue();
    }
}
