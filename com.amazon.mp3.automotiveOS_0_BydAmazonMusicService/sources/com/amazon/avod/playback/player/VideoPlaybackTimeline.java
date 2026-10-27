package com.amazon.avod.playback.player;

import com.amazon.avod.media.framework.error.InvalidRendererTimestampException;
import com.amazon.avod.media.playback.support.VideoRenderer;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public class VideoPlaybackTimeline {
    private final VideoRenderer mRenderer;
    private final Object mMutex = new Object();
    private long mLastObservedTimeInNanoseconds = 0;
    private long mOverrideSetAtInNanoseconds = 0;
    private long mOverrideTimeInNanoseconds = -1;

    public VideoPlaybackTimeline(VideoRenderer videoRenderer) {
        this.mRenderer = (VideoRenderer) Preconditions.checkNotNull(videoRenderer);
    }

    public void setObservedTimeInNanos(long j) {
        synchronized (this.mMutex) {
            this.mLastObservedTimeInNanoseconds = j;
        }
    }

    public void setOverrideTimeInNanos(long j) {
        synchronized (this.mMutex) {
            this.mOverrideTimeInNanoseconds = j;
            this.mOverrideSetAtInNanoseconds = this.mLastObservedTimeInNanoseconds;
        }
    }

    public long getCurrentPlayTimeInNanos() {
        synchronized (this.mMutex) {
            try {
                try {
                    long lastRenderedTimeStampInNanos = this.mRenderer.getLastRenderedTimeStampInNanos();
                    this.mLastObservedTimeInNanoseconds = lastRenderedTimeStampInNanos;
                    long j = this.mOverrideTimeInNanoseconds;
                    if (j != -1 && this.mOverrideSetAtInNanoseconds == lastRenderedTimeStampInNanos) {
                        return j;
                    }
                    this.mOverrideSetAtInNanoseconds = 0L;
                    this.mOverrideTimeInNanoseconds = -1L;
                    return lastRenderedTimeStampInNanos;
                } catch (InvalidRendererTimestampException unused) {
                    long j2 = this.mOverrideTimeInNanoseconds;
                    return (j2 == -1 || this.mOverrideSetAtInNanoseconds != this.mLastObservedTimeInNanoseconds) ? this.mLastObservedTimeInNanoseconds : j2;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
