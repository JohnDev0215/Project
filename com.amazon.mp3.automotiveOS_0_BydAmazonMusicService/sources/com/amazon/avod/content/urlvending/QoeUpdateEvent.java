package com.amazon.avod.content.urlvending;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.util.DataUnit;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt___StringsKt;

/* JADX INFO: compiled from: QoeUpdateEvent.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 ,2\u00020\u0001:\u0001,B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010)\u001a\u00020(¢\u0006\u0004\b*\u0010+R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0010R\u0017\u0010\u0013\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\u0013\u0010\t\u001a\u0004\b\u0014\u0010\u000bR\u0017\u0010\u0015\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u000e\u001a\u0004\b\u0016\u0010\u0010R\u0017\u0010\u0017\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u000e\u001a\u0004\b\u0018\u0010\u0010R\u0017\u0010\u0019\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u000e\u001a\u0004\b\u001a\u0010\u0010R\u0017\u0010\u001b\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u000e\u001a\u0004\b\u001c\u0010\u0010R\u0017\u0010\u001d\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\u001d\u0010\t\u001a\u0004\b\u001e\u0010\u000bR\u0017\u0010\u001f\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u001f\u0010\u000e\u001a\u0004\b \u0010\u0010R\u0017\u0010!\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b!\u0010\u000e\u001a\u0004\b\"\u0010\u0010R\u001a\u0010$\u001a\u00020#8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'¨\u0006-"}, d2 = {"Lcom/amazon/avod/content/urlvending/QoeUpdateEvent;", "", "Lcom/amazon/avod/media/TimeSpan;", "eventTimeStamp", "Lcom/amazon/avod/media/TimeSpan;", "getEventTimeStamp", "()Lcom/amazon/avod/media/TimeSpan;", "", "qoeValue", "F", "getQoeValue", "()F", "", "bitrateKbps", "I", "getBitrateKbps", "()I", "frontBufferSizeMillis", "getFrontBufferSizeMillis", "bandwidthChangeKbps", "getBandwidthChangeKbps", "latencyChangeMillis", "getLatencyChangeMillis", "manifestLatencyChangeMillis", "getManifestLatencyChangeMillis", "timeSpentBufferingMillis", "getTimeSpentBufferingMillis", "numOfBufferingEvent", "getNumOfBufferingEvent", "bandwidthKbps", "getBandwidthKbps", "latencyMillis", "getLatencyMillis", "manifestLatencyMillis", "getManifestLatencyMillis", "", "valueStr", "Ljava/lang/String;", "getValueStr", "()Ljava/lang/String;", "Lcom/amazon/avod/content/urlvending/QoeAggregatorInterface;", "qoeAggregator", "<init>", "(Lcom/amazon/avod/media/TimeSpan;FLcom/amazon/avod/content/urlvending/QoeAggregatorInterface;)V", "Companion", "ATVPlayerHeuristics_release"}, k = 1, mv = {1, 6, 0})
public class QoeUpdateEvent {
    private final float bandwidthChangeKbps;
    private final float bandwidthKbps;
    private final int bitrateKbps;
    private final TimeSpan eventTimeStamp;
    private final int frontBufferSizeMillis;
    private final int latencyChangeMillis;
    private final int latencyMillis;
    private final int manifestLatencyChangeMillis;
    private final int manifestLatencyMillis;
    private final int numOfBufferingEvent;
    private final float qoeValue;
    private final int timeSpentBufferingMillis;
    private final String valueStr;

    public QoeUpdateEvent(TimeSpan eventTimeStamp, float f, QoeAggregatorInterface qoeAggregator) {
        Intrinsics.checkNotNullParameter(eventTimeStamp, "eventTimeStamp");
        Intrinsics.checkNotNullParameter(qoeAggregator, "qoeAggregator");
        this.eventTimeStamp = eventTimeStamp;
        this.qoeValue = f;
        DataUnit dataUnit = DataUnit.BITS;
        int kiloBits = (int) dataUnit.toKiloBits(qoeAggregator.getBitrateBps());
        this.bitrateKbps = kiloBits;
        int frontBufferSizeMillis = qoeAggregator.getFrontBufferSizeMillis();
        this.frontBufferSizeMillis = frontBufferSizeMillis;
        float kiloBits2 = dataUnit.toKiloBits(qoeAggregator.getBandwidthChangeBps());
        this.bandwidthChangeKbps = kiloBits2;
        int latencyChangeMillis = qoeAggregator.getLatencyChangeMillis();
        this.latencyChangeMillis = latencyChangeMillis;
        int manifestLatencyChangeMillis = qoeAggregator.getManifestLatencyChangeMillis();
        this.manifestLatencyChangeMillis = manifestLatencyChangeMillis;
        int timeSpentBufferingMillis = qoeAggregator.getTimeSpentBufferingMillis();
        this.timeSpentBufferingMillis = timeSpentBufferingMillis;
        int numOfBufferingEvent = qoeAggregator.getNumOfBufferingEvent();
        this.numOfBufferingEvent = numOfBufferingEvent;
        float kiloBits3 = dataUnit.toKiloBits(qoeAggregator.getBandwidthBps());
        this.bandwidthKbps = kiloBits3;
        int latencyMillis = qoeAggregator.getLatencyMillis();
        this.latencyMillis = latencyMillis;
        int manifestLatencyMillis = qoeAggregator.getManifestLatencyMillis();
        this.manifestLatencyMillis = manifestLatencyMillis;
        StringBuilder sb = new StringBuilder();
        String str = String.format("QoE:%.2f,", Arrays.copyOf(new Object[]{Float.valueOf(f)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(this, *args)");
        sb.append(str);
        sb.append("bitrate:");
        sb.append(kiloBits);
        sb.append(",buffer:");
        sb.append(frontBufferSizeMillis);
        sb.append(',');
        String str2 = String.format("BWChange:%.2f,", Arrays.copyOf(new Object[]{Float.valueOf(kiloBits2)}, 1));
        Intrinsics.checkNotNullExpressionValue(str2, "format(this, *args)");
        sb.append(str2);
        sb.append("LATChange:");
        sb.append(latencyChangeMillis);
        sb.append(",mLATChange:");
        sb.append(manifestLatencyChangeMillis);
        sb.append(",tsb:");
        sb.append(timeSpentBufferingMillis);
        sb.append(",count:");
        sb.append(numOfBufferingEvent);
        sb.append(',');
        String str3 = String.format("BW:%.2f,", Arrays.copyOf(new Object[]{Float.valueOf(kiloBits3)}, 1));
        Intrinsics.checkNotNullExpressionValue(str3, "format(this, *args)");
        sb.append(str3);
        sb.append("LAT:");
        sb.append(latencyMillis);
        sb.append(",mLAT:");
        sb.append(manifestLatencyMillis);
        this.valueStr = StringsKt___StringsKt.take(sb.toString(), 200);
    }

    public final float getQoeValue() {
        return this.qoeValue;
    }

    public final int getBitrateKbps() {
        return this.bitrateKbps;
    }

    public final int getFrontBufferSizeMillis() {
        return this.frontBufferSizeMillis;
    }

    public final float getBandwidthChangeKbps() {
        return this.bandwidthChangeKbps;
    }

    public final int getLatencyChangeMillis() {
        return this.latencyChangeMillis;
    }

    public final int getManifestLatencyChangeMillis() {
        return this.manifestLatencyChangeMillis;
    }

    public final int getTimeSpentBufferingMillis() {
        return this.timeSpentBufferingMillis;
    }

    public final int getNumOfBufferingEvent() {
        return this.numOfBufferingEvent;
    }

    public final float getBandwidthKbps() {
        return this.bandwidthKbps;
    }

    public final int getLatencyMillis() {
        return this.latencyMillis;
    }

    public final int getManifestLatencyMillis() {
        return this.manifestLatencyMillis;
    }

    public String getValueStr() {
        return this.valueStr;
    }
}
