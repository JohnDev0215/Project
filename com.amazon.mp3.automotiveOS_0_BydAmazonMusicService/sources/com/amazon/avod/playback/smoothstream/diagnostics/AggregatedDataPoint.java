package com.amazon.avod.playback.smoothstream.diagnostics;

import com.amazon.avod.media.TimeSpan;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AggregatedDataPoint.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/amazon/avod/playback/smoothstream/diagnostics/AggregatedDataPoint;", "Lcom/amazon/avod/playback/smoothstream/diagnostics/DataPoint;", "timeStamp", "Lcom/amazon/avod/media/TimeSpan;", "aggregatedBandwidthBps", "", "aggregatedLatencyMillis", "qoeValue", "", "(Lcom/amazon/avod/media/TimeSpan;IIF)V", "getAggregatedBandwidthBps", "()I", "getAggregatedLatencyMillis", "getQoeValue", "()F", "ATVAndroidPlayback_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AggregatedDataPoint extends DataPoint {
    private final int aggregatedBandwidthBps;
    private final int aggregatedLatencyMillis;
    private final float qoeValue;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AggregatedDataPoint(TimeSpan timeStamp, int i, int i2, float f) {
        super(timeStamp);
        Intrinsics.checkNotNullParameter(timeStamp, "timeStamp");
        this.aggregatedBandwidthBps = i;
        this.aggregatedLatencyMillis = i2;
        this.qoeValue = f;
    }

    public final int getAggregatedBandwidthBps() {
        return this.aggregatedBandwidthBps;
    }

    public final int getAggregatedLatencyMillis() {
        return this.aggregatedLatencyMillis;
    }

    public final float getQoeValue() {
        return this.qoeValue;
    }
}
