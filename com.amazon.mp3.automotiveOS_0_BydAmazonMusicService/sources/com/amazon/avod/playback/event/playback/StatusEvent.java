package com.amazon.avod.playback.event.playback;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.playback.support.RendererPerformanceData;
import com.amazon.avod.playback.event.PlaybackEvent;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class StatusEvent extends PlaybackEvent {
    private final String mLastPeriodId;
    private final long mLastRenderedTimestampInNanoseconds;
    private final long mLastSubmittedAudioToNativeInNanoseconds;
    private final long mLastSubmittedVideoToNativeInNanoseconds;
    private final RendererPerformanceData mRendererPerformanceData;

    public StatusEvent(long j, long j2, long j3, long j4, RendererPerformanceData rendererPerformanceData, @Nullable String str) {
        super(new TimeSpan(j));
        this.mLastRenderedTimestampInNanoseconds = j2;
        this.mLastSubmittedVideoToNativeInNanoseconds = j3;
        this.mLastSubmittedAudioToNativeInNanoseconds = j4;
        this.mRendererPerformanceData = rendererPerformanceData;
        this.mLastPeriodId = str;
    }

    public long getLastRenderedTimestampInNanoseconds() {
        return this.mLastRenderedTimestampInNanoseconds;
    }

    public long getLastSubmittedAudioToNativeInNanos() {
        return this.mLastSubmittedAudioToNativeInNanoseconds;
    }

    public long getLastSubmittedVideoToNativeInNanos() {
        return this.mLastSubmittedVideoToNativeInNanoseconds;
    }

    @Nullable
    public String getLastPeriodId() {
        return this.mLastPeriodId;
    }

    public RendererPerformanceData getRendererPerformanceData() {
        return this.mRendererPerformanceData;
    }
}
