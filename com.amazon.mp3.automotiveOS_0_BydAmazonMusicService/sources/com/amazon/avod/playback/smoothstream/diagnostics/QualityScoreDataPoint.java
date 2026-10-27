package com.amazon.avod.playback.smoothstream.diagnostics;

import com.amazon.avod.media.TimeSpan;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QualityScoreDataPoint.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/amazon/avod/playback/smoothstream/diagnostics/QualityScoreDataPoint;", "Lcom/amazon/avod/playback/smoothstream/diagnostics/DataPoint;", "timeStamp", "Lcom/amazon/avod/media/TimeSpan;", "qualityScore", "", "avgMediaBitrateMbps", "", "(Lcom/amazon/avod/media/TimeSpan;DF)V", "getAvgMediaBitrateMbps", "()F", "getQualityScore", "()D", "ATVAndroidPlayback_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class QualityScoreDataPoint extends DataPoint {
    private final float avgMediaBitrateMbps;
    private final double qualityScore;

    public final double getQualityScore() {
        return this.qualityScore;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QualityScoreDataPoint(TimeSpan timeStamp, double d, float f) {
        super(timeStamp);
        Intrinsics.checkNotNullParameter(timeStamp, "timeStamp");
        this.qualityScore = d;
        this.avgMediaBitrateMbps = f;
    }

    public final float getAvgMediaBitrateMbps() {
        return this.avgMediaBitrateMbps;
    }
}
