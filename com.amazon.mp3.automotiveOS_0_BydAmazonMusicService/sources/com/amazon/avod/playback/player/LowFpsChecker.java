package com.amazon.avod.playback.player;

import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.event.playback.LowFpsEvent;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class LowFpsChecker {
    private final int mConsecutiveLowFpsIntervalsRequired;
    private final PlaybackEventTransport mEventTransport;
    private final long mRealtimeDriftThresholdInNanoseconds;
    private final long mRendererTimeCheckIntervalInNanoseconds;
    private long mLastRealtimeRenderedCheckInNanoseconds = 0;
    private long mLastRenderedTimestampInNanoseconds = 0;
    private int mConsecutiveLowFpsIntervals = 0;

    public LowFpsChecker(PlaybackConfig playbackConfig, PlaybackEventTransport playbackEventTransport) {
        this.mRendererTimeCheckIntervalInNanoseconds = playbackConfig.getRenderedTimeCheckIntervalMillis().getTotalNanoSeconds();
        this.mRealtimeDriftThresholdInNanoseconds = playbackConfig.getRealtimeDriftThresholdMillis().getTotalNanoSeconds();
        this.mConsecutiveLowFpsIntervalsRequired = playbackConfig.getConsecutiveLowFpsIntervalsRequired();
        this.mEventTransport = playbackEventTransport;
    }

    public void reset(long j) {
        resetRenderedTimeAndRealTime(j);
        this.mConsecutiveLowFpsIntervals = 0;
    }

    private void resetRenderedTimeAndRealTime(long j) {
        this.mLastRealtimeRenderedCheckInNanoseconds = TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
        this.mLastRenderedTimestampInNanoseconds = j;
    }

    public void validateRenderedTimesMatchRealTime(long j) {
        long jAbs = Math.abs(j - this.mLastRenderedTimestampInNanoseconds);
        long jAbs2 = Math.abs(this.mLastRealtimeRenderedCheckInNanoseconds - TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis()));
        if (jAbs2 > this.mRendererTimeCheckIntervalInNanoseconds) {
            if (Math.abs(jAbs2 - jAbs) > this.mRealtimeDriftThresholdInNanoseconds) {
                int i = this.mConsecutiveLowFpsIntervals + 1;
                this.mConsecutiveLowFpsIntervals = i;
                if (i > this.mConsecutiveLowFpsIntervalsRequired) {
                    this.mEventTransport.postEvent(new LowFpsEvent(new TimeSpan(j), new TimeSpan(jAbs), new TimeSpan(jAbs2), this.mConsecutiveLowFpsIntervals));
                    this.mConsecutiveLowFpsIntervals = 0;
                }
            } else {
                this.mConsecutiveLowFpsIntervals = 0;
            }
            resetRenderedTimeAndRealTime(j);
        }
    }
}
