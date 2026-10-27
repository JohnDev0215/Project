package com.amazon.avod.playback.player;

import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.playback.support.RendererPerformanceData;
import com.amazon.avod.media.playback.support.RendererPerformanceEventListener;
import java.util.Iterator;
import java.util.LinkedList;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class PlaybackPerformanceReporter implements RendererPerformanceEventListener {
    private final PlaybackEventTransport mEventTransport;
    private final PlaybackPerformanceConfig mPlaybackPerformanceConfig;
    private final LinkedList<PerformanceSample> mSamples;
    private double mSamplesValue;
    private final Object mSync;

    private static class PerformanceSample {
        private final TimeSpan mSampleTime;
        private final float mSampleValue;

        public PerformanceSample(float f, TimeSpan timeSpan) {
            this.mSampleValue = f;
            this.mSampleTime = timeSpan;
        }

        public float getSampleValue() {
            return this.mSampleValue;
        }

        public TimeSpan getSampleTime() {
            return this.mSampleTime;
        }
    }

    public PlaybackPerformanceReporter(PlaybackEventTransport playbackEventTransport) {
        this(PlaybackPerformanceConfig.INSTANCE, playbackEventTransport);
    }

    PlaybackPerformanceReporter(PlaybackPerformanceConfig playbackPerformanceConfig, PlaybackEventTransport playbackEventTransport) {
        this.mSync = new Object();
        this.mPlaybackPerformanceConfig = playbackPerformanceConfig;
        this.mEventTransport = playbackEventTransport;
        this.mSamples = new LinkedList<>();
        this.mSamplesValue = 0.0d;
    }

    public void reset() {
        synchronized (this.mSync) {
            this.mSamples.clear();
            this.mSamplesValue = 0.0d;
        }
    }

    @Override // com.amazon.avod.media.playback.support.RendererPerformanceEventListener
    public void onLowPerformanceReported(RendererPerformanceData rendererPerformanceData) {
        onLowPerformanceReported(rendererPerformanceData, TimeSpan.now());
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00db A[Catch: all -> 0x00f4, TryCatch #0 {, blocks: (B:4:0x0006, B:6:0x001c, B:8:0x001e, B:10:0x002b, B:23:0x00c0, B:27:0x00d1, B:29:0x00db, B:30:0x00f2, B:11:0x0051, B:13:0x0074, B:15:0x0081, B:20:0x009c, B:16:0x0086, B:18:0x0093, B:19:0x0098), top: B:35:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void onLowPerformanceReported(com.amazon.avod.media.playback.support.RendererPerformanceData r14, com.amazon.avod.media.TimeSpan r15) {
        /*
            Method dump skipped, instruction units count: 247
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.playback.player.PlaybackPerformanceReporter.onLowPerformanceReported(com.amazon.avod.media.playback.support.RendererPerformanceData, com.amazon.avod.media.TimeSpan):void");
    }

    private float getSampleValue(@Nonnull RendererPerformanceData rendererPerformanceData) {
        return (rendererPerformanceData.getSourceDropNum() * this.mPlaybackPerformanceConfig.getSourceDropCoefficient()) + (rendererPerformanceData.getCodecDropNum() * this.mPlaybackPerformanceConfig.getCodecDropCoefficient()) + (rendererPerformanceData.getRenderDropNum() * this.mPlaybackPerformanceConfig.getRenderDropCoefficient()) + (rendererPerformanceData.getSourceTimeNum() * this.mPlaybackPerformanceConfig.getSourceTimeCoefficient()) + (rendererPerformanceData.getCodecTimeNum() * this.mPlaybackPerformanceConfig.getCodecTimeCoefficient()) + (rendererPerformanceData.getRenderTimeNum() * this.mPlaybackPerformanceConfig.getRenderTimeCoefficient()) + (rendererPerformanceData.getJitterNum() * this.mPlaybackPerformanceConfig.getJitterNumCoefficient());
    }

    @Nullable
    private TimeSpan purgeSamplesGetEarliest(@Nonnull TimeSpan timeSpan, @Nonnull TimeSpan timeSpan2) {
        Iterator<PerformanceSample> it = this.mSamples.iterator();
        while (it.hasNext()) {
            PerformanceSample next = it.next();
            if (TimeSpan.add(next.getSampleTime(), timeSpan).greaterThanEquals(timeSpan2)) {
                return next.getSampleTime();
            }
            it.remove();
            this.mSamplesValue -= (double) next.getSampleValue();
        }
        return null;
    }
}
