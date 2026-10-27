package com.amazon.avod.playback;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolder;
import com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.playback.support.RendererPerformanceData;
import com.amazon.avod.media.playback.util.SlidingWindowEventTracker;
import com.amazon.avod.playback.event.playback.HighFrameRateQualityEvent;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.util.DLog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HighFrameRatePlayerPerformanceEvaluator.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B%\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ \u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000eH\u0002J/\u0010\u001a\u001a\u00020\u00142\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002¢\u0006\u0002\u0010\u001fJ\u0010\u0010 \u001a\u00020\u00142\b\u0010!\u001a\u0004\u0018\u00010\u0012J\u0018\u0010\"\u001a\u00020\u00142\u0006\u0010#\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016J \u0010$\u001a\u00020\u00142\u0006\u0010%\u001a\u00020&2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J \u0010'\u001a\u00020\u00142\u0006\u0010%\u001a\u00020&2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001eH\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/amazon/avod/playback/HighFrameRatePlayerPerformanceEvaluator;", "", "playbackEventBus", "Lcom/amazon/avod/event/PlaybackEventTransport;", "(Lcom/amazon/avod/event/PlaybackEventTransport;)V", "qualityHolder", "Lcom/amazon/avod/content/smoothstream/quality/HighFrameRateQualityHolderInterface;", "burstFrameDropEventTracker", "Lcom/amazon/avod/media/playback/util/SlidingWindowEventTracker;", "continualFrameDropEventTracker", "(Lcom/amazon/avod/content/smoothstream/quality/HighFrameRateQualityHolderInterface;Lcom/amazon/avod/media/playback/util/SlidingWindowEventTracker;Lcom/amazon/avod/media/playback/util/SlidingWindowEventTracker;Lcom/amazon/avod/event/PlaybackEventTransport;)V", "isHFRPerformanceEvaluatorEnabled", "", "lastFrameDropCount", "", "lastTimeStamp", "Lcom/amazon/avod/media/TimeSpan;", "playbackSessionContext", "Lcom/amazon/avod/playback/session/PlaybackSessionContext;", "applyDynamicQualityCap", "", "performanceData", "Lcom/amazon/avod/media/playback/support/RendererPerformanceData;", "fourCC", "", "currentBitrateCap", "evaluateFrameDropAnomaly", "timeDeltaMillis", "", "maxFramesPerSecond", "", "(Ljava/lang/Long;Lcom/amazon/avod/media/playback/support/RendererPerformanceData;IF)V", "initialize", "playbackContext", "processPerformanceData", "eventTimeStamp", "recordBurstFrameDropAnomaly", "frameDropPercentage", "", "recordContinualFrameDropAnomaly", "ATVAndroidPlayback_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class HighFrameRatePlayerPerformanceEvaluator {
    private final SlidingWindowEventTracker burstFrameDropEventTracker;
    private final SlidingWindowEventTracker continualFrameDropEventTracker;
    private final boolean isHFRPerformanceEvaluatorEnabled;
    private int lastFrameDropCount;
    private TimeSpan lastTimeStamp;
    private final PlaybackEventTransport playbackEventBus;
    private PlaybackSessionContext playbackSessionContext;
    private final HighFrameRateQualityHolderInterface qualityHolder;

    public HighFrameRatePlayerPerformanceEvaluator(HighFrameRateQualityHolderInterface qualityHolder, SlidingWindowEventTracker burstFrameDropEventTracker, SlidingWindowEventTracker continualFrameDropEventTracker, PlaybackEventTransport playbackEventBus) {
        Intrinsics.checkNotNullParameter(qualityHolder, "qualityHolder");
        Intrinsics.checkNotNullParameter(burstFrameDropEventTracker, "burstFrameDropEventTracker");
        Intrinsics.checkNotNullParameter(continualFrameDropEventTracker, "continualFrameDropEventTracker");
        Intrinsics.checkNotNullParameter(playbackEventBus, "playbackEventBus");
        this.qualityHolder = qualityHolder;
        this.burstFrameDropEventTracker = burstFrameDropEventTracker;
        this.continualFrameDropEventTracker = continualFrameDropEventTracker;
        this.playbackEventBus = playbackEventBus;
        this.isHFRPerformanceEvaluatorEnabled = qualityHolder.isHFRPerformanceEvaluatorEnabled();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public HighFrameRatePlayerPerformanceEvaluator(PlaybackEventTransport playbackEventBus) {
        Intrinsics.checkNotNullParameter(playbackEventBus, "playbackEventBus");
        HighFrameRateQualityHolder highFrameRateQualityHolder = HighFrameRateQualityHolder.INSTANCE;
        this(highFrameRateQualityHolder, new SlidingWindowEventTracker(highFrameRateQualityHolder.getBurstFrameDropWindowLength(), highFrameRateQualityHolder.getBurstFrameDropWindowThreshold()), new SlidingWindowEventTracker(highFrameRateQualityHolder.getContinualFrameDropWindowLength(), highFrameRateQualityHolder.getContinualFrameDropWindowThreshold()), playbackEventBus);
    }

    public final void initialize(PlaybackSessionContext playbackContext) {
        this.playbackSessionContext = playbackContext;
    }

    public final void processPerformanceData(TimeSpan eventTimeStamp, RendererPerformanceData performanceData) {
        ContentSession contentSession;
        StreamIndex videoStream;
        Intrinsics.checkNotNullParameter(eventTimeStamp, "eventTimeStamp");
        if (!this.isHFRPerformanceEvaluatorEnabled || performanceData == null || performanceData.getLastDecodedSampleBitrate() <= 0) {
            return;
        }
        TimeSpan timeSpan = this.lastTimeStamp;
        Long lValueOf = timeSpan == null ? null : Long.valueOf(eventTimeStamp.getTotalMilliseconds() - timeSpan.getTotalMilliseconds());
        PlaybackSessionContext playbackSessionContext = this.playbackSessionContext;
        if (playbackSessionContext != null && (contentSession = playbackSessionContext.getContentSession()) != null && (videoStream = contentSession.getContext().getManifest().getVideoStream()) != null) {
            String fourCC = videoStream.getFourCC();
            Intrinsics.checkNotNullExpressionValue(fourCC, "videoStreamIndex.fourCC");
            Integer bitrateCap = this.qualityHolder.getBitrateCap(fourCC);
            int iMin = Math.min(performanceData.getLastDecodedSampleBitrate(), bitrateCap == null ? Integer.MAX_VALUE : bitrateCap.intValue());
            evaluateFrameDropAnomaly(lValueOf, performanceData, iMin, videoStream.getMaxFrameRate());
            applyDynamicQualityCap(performanceData, fourCC, iMin);
        }
        this.lastTimeStamp = eventTimeStamp;
        this.lastFrameDropCount = performanceData.getTotalRenderDropNum();
    }

    private final void evaluateFrameDropAnomaly(Long timeDeltaMillis, RendererPerformanceData performanceData, int currentBitrateCap, float maxFramesPerSecond) {
        if (timeDeltaMillis == null || timeDeltaMillis.longValue() <= 0) {
            return;
        }
        float totalSeconds = TimeSpan.fromMilliseconds(timeDeltaMillis.longValue()).getTotalSeconds() * maxFramesPerSecond;
        if (maxFramesPerSecond < this.qualityHolder.getMinimumFrameRateRendererPerformanceEvaluation() || !this.qualityHolder.isHighFrameRate(maxFramesPerSecond)) {
            return;
        }
        double totalRenderDropNum = (((double) (performanceData.getTotalRenderDropNum() - this.lastFrameDropCount)) * 100.0d) / ((double) totalSeconds);
        recordBurstFrameDropAnomaly(totalRenderDropNum, performanceData, maxFramesPerSecond);
        recordContinualFrameDropAnomaly(totalRenderDropNum, performanceData, maxFramesPerSecond);
    }

    private final void recordBurstFrameDropAnomaly(double frameDropPercentage, RendererPerformanceData performanceData, float maxFramesPerSecond) {
        if (!this.qualityHolder.getShouldTrackFrameDropBurst() || frameDropPercentage < this.qualityHolder.getBurstFrameDropDetectionPercentage()) {
            return;
        }
        String str = "a burst of FrameDrop detected, fps: " + maxFramesPerSecond + " %frameDrop: " + frameDropPercentage + " lastDecodedBitrate " + performanceData.getLastDecodedSampleBitrate();
        DLog.logf(Intrinsics.stringPlus("HFRPerf ", str));
        this.burstFrameDropEventTracker.recordEvent(TimeSpan.now());
        if (this.qualityHolder.getShouldReportAllFrameDropAnomalies()) {
            PlaybackEventTransport playbackEventTransport = this.playbackEventBus;
            TimeSpan timeSpanNow = TimeSpan.now();
            Intrinsics.checkNotNullExpressionValue(timeSpanNow, "now()");
            playbackEventTransport.postEvent(new HighFrameRateQualityEvent(timeSpanNow, str));
        }
    }

    private final void recordContinualFrameDropAnomaly(double frameDropPercentage, RendererPerformanceData performanceData, float maxFramesPerSecond) {
        if (!this.qualityHolder.getShouldTrackFrameDropContinual() || frameDropPercentage < this.qualityHolder.getContinualFrameDropDetectionPercentage()) {
            return;
        }
        String str = "a continual FrameDrop event detected, fps: " + maxFramesPerSecond + " %frameDrop: " + frameDropPercentage + " lastDecodedBitrate: " + performanceData.getLastDecodedSampleBitrate();
        DLog.logf(Intrinsics.stringPlus("HFRPerf  ", str));
        this.continualFrameDropEventTracker.recordEvent(TimeSpan.now());
        if (this.qualityHolder.getShouldReportAllFrameDropAnomalies()) {
            PlaybackEventTransport playbackEventTransport = this.playbackEventBus;
            TimeSpan timeSpanNow = TimeSpan.now();
            Intrinsics.checkNotNullExpressionValue(timeSpanNow, "now()");
            playbackEventTransport.postEvent(new HighFrameRateQualityEvent(timeSpanNow, str));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00e9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void applyDynamicQualityCap(com.amazon.avod.media.playback.support.RendererPerformanceData r7, java.lang.String r8, int r9) {
        /*
            Method dump skipped, instruction units count: 511
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.playback.HighFrameRatePlayerPerformanceEvaluator.applyDynamicQualityCap(com.amazon.avod.media.playback.support.RendererPerformanceData, java.lang.String, int):void");
    }
}
