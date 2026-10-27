package com.amazon.scrublib.models;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PeakRaw.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0019\u0010\u001aJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R$\u0010\t\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R$\u0010\u0016\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\n\u001a\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/amazon/scrublib/models/PeakRaw;", "", "", "toString", "", "hashCode", "other", "", "equals", "timeStampIdx", "Ljava/lang/Integer;", "getTimeStampIdx", "()Ljava/lang/Integer;", "setTimeStampIdx", "(Ljava/lang/Integer;)V", "", "amplitude", "Ljava/lang/Double;", "getAmplitude", "()Ljava/lang/Double;", "setAmplitude", "(Ljava/lang/Double;)V", "frequencyIdx", "getFrequencyIdx", "setFrequencyIdx", "<init>", "(Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/Integer;)V", "DMPScrubCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class PeakRaw {
    private Double amplitude;
    private Integer frequencyIdx;
    private Integer timeStampIdx;

    public PeakRaw() {
        this(null, null, null, 7, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PeakRaw)) {
            return false;
        }
        PeakRaw peakRaw = (PeakRaw) other;
        return Intrinsics.areEqual(this.timeStampIdx, peakRaw.timeStampIdx) && Intrinsics.areEqual((Object) this.amplitude, (Object) peakRaw.amplitude) && Intrinsics.areEqual(this.frequencyIdx, peakRaw.frequencyIdx);
    }

    public int hashCode() {
        Integer num = this.timeStampIdx;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        Double d = this.amplitude;
        int iHashCode2 = (iHashCode + (d == null ? 0 : d.hashCode())) * 31;
        Integer num2 = this.frequencyIdx;
        return iHashCode2 + (num2 != null ? num2.hashCode() : 0);
    }

    public String toString() {
        return "PeakRaw(timeStampIdx=" + this.timeStampIdx + ", amplitude=" + this.amplitude + ", frequencyIdx=" + this.frequencyIdx + ')';
    }

    public PeakRaw(Integer num, Double d, Integer num2) {
        this.timeStampIdx = num;
        this.amplitude = d;
        this.frequencyIdx = num2;
    }

    public /* synthetic */ PeakRaw(Integer num, Double d, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : d, (i & 4) != 0 ? null : num2);
    }

    public final Integer getTimeStampIdx() {
        return this.timeStampIdx;
    }

    public final Double getAmplitude() {
        return this.amplitude;
    }

    public final Integer getFrequencyIdx() {
        return this.frequencyIdx;
    }
}
