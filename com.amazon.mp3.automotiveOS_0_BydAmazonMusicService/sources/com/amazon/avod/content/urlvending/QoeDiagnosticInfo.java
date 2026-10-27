package com.amazon.avod.content.urlvending;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QoeDiagnosticInfo.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0007\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\u0004\u0012\u0006\u0010\u0010\u001a\u00020\u0004\u0012\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0017\u0010\u0018B\u0011\b\u0016\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0004\b\u0017\u0010\u0019J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\t\u0010\u000bR\u0017\u0010\f\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u000fR\u0017\u0010\u0013\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001a"}, d2 = {"Lcom/amazon/avod/content/urlvending/QoeDiagnosticInfo;", "", "", "toString", "", "hashCode", "other", "", "equals", "isStarted", "Z", "()Z", "bandwidthBps", "I", "getBandwidthBps", "()I", "latencyMillis", "getLatencyMillis", "", "qoeValue", "F", "getQoeValue", "()F", "<init>", "(ZIIF)V", "(Z)V", "ATVPlayerHeuristics_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class QoeDiagnosticInfo {
    private final int bandwidthBps;
    private final boolean isStarted;
    private final int latencyMillis;
    private final float qoeValue;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QoeDiagnosticInfo)) {
            return false;
        }
        QoeDiagnosticInfo qoeDiagnosticInfo = (QoeDiagnosticInfo) other;
        return this.isStarted == qoeDiagnosticInfo.isStarted && this.bandwidthBps == qoeDiagnosticInfo.bandwidthBps && this.latencyMillis == qoeDiagnosticInfo.latencyMillis && Intrinsics.areEqual((Object) Float.valueOf(this.qoeValue), (Object) Float.valueOf(qoeDiagnosticInfo.qoeValue));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    public int hashCode() {
        boolean z = this.isStarted;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        return (((((r0 * 31) + Integer.hashCode(this.bandwidthBps)) * 31) + Integer.hashCode(this.latencyMillis)) * 31) + Float.hashCode(this.qoeValue);
    }

    public String toString() {
        return "QoeDiagnosticInfo(isStarted=" + this.isStarted + ", bandwidthBps=" + this.bandwidthBps + ", latencyMillis=" + this.latencyMillis + ", qoeValue=" + this.qoeValue + ')';
    }

    public QoeDiagnosticInfo(boolean z, int i, int i2, float f) {
        this.isStarted = z;
        this.bandwidthBps = i;
        this.latencyMillis = i2;
        this.qoeValue = f;
    }

    /* JADX INFO: renamed from: isStarted, reason: from getter */
    public final boolean getIsStarted() {
        return this.isStarted;
    }

    public final int getBandwidthBps() {
        return this.bandwidthBps;
    }

    public final int getLatencyMillis() {
        return this.latencyMillis;
    }

    public final float getQoeValue() {
        return this.qoeValue;
    }

    public QoeDiagnosticInfo(boolean z) {
        this(z, 0, 0, 0.0f);
    }
}
