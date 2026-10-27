package com.amazon.music.metrics.mts.event.types.launchlatencyinfo;

import kotlin.Metadata;

/* JADX INFO: compiled from: LaunchLatencyCode.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0003X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007j\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/amazon/music/metrics/mts/event/types/launchlatencyinfo/LaunchLatencyCode;", "", "latencyCode", "", "subtype", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getLatencyCode", "()Ljava/lang/String;", "operation", "getOperation", "getSubtype", "COLD_START", "DMMMetricsMTSEvents_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum LaunchLatencyCode {
    COLD_START("launchColdStart", "coldStart");

    private final String latencyCode;
    private final String operation = "launch";
    private final String subtype;

    LaunchLatencyCode(String str, String str2) {
        this.latencyCode = str;
        this.subtype = str2;
    }

    public final String getLatencyCode() {
        return this.latencyCode;
    }

    public final String getSubtype() {
        return this.subtype;
    }

    public final String getOperation() {
        return this.operation;
    }
}
