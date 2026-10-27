package com.amazon.music.metrics.mts.event.types.pagelatencyinfo;

import com.google.firebase.perf.plugin.util.AsmString;
import kotlin.Metadata;

/* JADX INFO: compiled from: LatencyTrackingLeg.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\r\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/LatencyTrackingLeg;", "", "latencyTrackingLeg", "", "order", "", "(Ljava/lang/String;ILjava/lang/String;I)V", "getLatencyTrackingLeg", "()Ljava/lang/String;", "getOrder", "()I", "START", "INITIALIZING", "REQUEST", "RESPONSE", "PROCESSING", "RENDERING", "LOAD_COMPLETE", "DMMMetricsMTSEvents_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum LatencyTrackingLeg {
    START(AsmString.METHOD_FIREBASE_PERF_TRACE_START, 0),
    INITIALIZING("initializing", 1),
    REQUEST("request", 2),
    RESPONSE("response", 3),
    PROCESSING("processing", 4),
    RENDERING("rendering", 5),
    LOAD_COMPLETE("load_complete", Integer.MAX_VALUE);

    private final String latencyTrackingLeg;
    private final int order;

    LatencyTrackingLeg(String str, int i) {
        this.latencyTrackingLeg = str;
        this.order = i;
    }

    public final String getLatencyTrackingLeg() {
        return this.latencyTrackingLeg;
    }

    public final int getOrder() {
        return this.order;
    }
}
