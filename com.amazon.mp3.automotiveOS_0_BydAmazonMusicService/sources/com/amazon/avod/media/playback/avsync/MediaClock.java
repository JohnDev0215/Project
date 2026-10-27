package com.amazon.avod.media.playback.avsync;

import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class MediaClock {

    @Nonnull
    private TimeSource mActualTimeSource;
    private double mCurrentInterpolationRatio;
    private final TimeSource mInterpolationTimeSource;
    private long mLastSyncActualTimeUs;
    private long mLastSyncInterpolationTimeUs;
    private final Object mMutex;
    private long mStartActualTimeUs;
    private long mStartPresentationTimeUs;

    public MediaClock() {
        this(TimeSource.DEFAULT_INSTANCE);
    }

    MediaClock(@Nonnull TimeSource timeSource) {
        this.mMutex = new Object();
        this.mLastSyncInterpolationTimeUs = -1L;
        this.mLastSyncActualTimeUs = -1L;
        this.mCurrentInterpolationRatio = -1.0d;
        this.mStartActualTimeUs = -1L;
        this.mStartPresentationTimeUs = -1L;
        TimeSource timeSource2 = (TimeSource) Preconditions.checkNotNull(timeSource, "interpolationTimeSource");
        this.mInterpolationTimeSource = timeSource2;
        this.mActualTimeSource = timeSource2;
    }

    public void setTimeSource(@Nonnull TimeSource timeSource) {
        synchronized (this.mMutex) {
            Preconditions.checkState(this.mStartActualTimeUs == -1, "Must call setTimeSource(..) before start(..)!");
            this.mActualTimeSource = (TimeSource) Preconditions.checkNotNull(timeSource, "timeSource");
        }
    }

    public void start(long j) {
        synchronized (this.mMutex) {
            Preconditions.checkState(this.mStartActualTimeUs == -1, "Can't call start(..) twice in a row!");
            this.mLastSyncInterpolationTimeUs = -1L;
            this.mLastSyncActualTimeUs = -1L;
            this.mCurrentInterpolationRatio = -1.0d;
            this.mStartActualTimeUs = this.mActualTimeSource.getCurrentRealTimeUs();
            this.mStartPresentationTimeUs = j;
        }
    }

    public long pause() {
        long currentMediaTimeUs;
        synchronized (this.mMutex) {
            currentMediaTimeUs = getCurrentMediaTimeUs();
            this.mLastSyncInterpolationTimeUs = -1L;
            this.mLastSyncActualTimeUs = -1L;
            this.mCurrentInterpolationRatio = -1.0d;
        }
        return currentMediaTimeUs;
    }

    public boolean isStarted() {
        boolean z;
        synchronized (this.mMutex) {
            z = this.mStartActualTimeUs != -1;
        }
        return z;
    }

    public long stop() {
        long currentMediaTimeUs;
        synchronized (this.mMutex) {
            currentMediaTimeUs = getCurrentMediaTimeUs();
            this.mLastSyncInterpolationTimeUs = -1L;
            this.mLastSyncActualTimeUs = -1L;
            this.mCurrentInterpolationRatio = -1.0d;
            this.mStartActualTimeUs = -1L;
            this.mStartPresentationTimeUs = -1L;
        }
        return currentMediaTimeUs;
    }

    public long getCurrentMediaTimeUs() {
        synchronized (this.mMutex) {
            if (this.mStartPresentationTimeUs == -1) {
                return 0L;
            }
            long currentRealTimeUs = this.mInterpolationTimeSource.getCurrentRealTimeUs();
            long j = currentRealTimeUs - this.mLastSyncInterpolationTimeUs;
            if (Math.abs(this.mCurrentInterpolationRatio - (-1.0d)) > 1.0E-6d && j < 500000) {
                return this.mStartPresentationTimeUs + (this.mLastSyncActualTimeUs - this.mStartActualTimeUs) + ((long) (j * this.mCurrentInterpolationRatio));
            }
            long currentRealTimeUs2 = this.mActualTimeSource.getCurrentRealTimeUs();
            long j2 = this.mLastSyncActualTimeUs;
            long j3 = currentRealTimeUs2 - j2;
            if (j3 > 500000 && j > 0) {
                if (j2 != -1 && this.mLastSyncInterpolationTimeUs != -1) {
                    this.mCurrentInterpolationRatio = j3 / j;
                }
                this.mLastSyncActualTimeUs = currentRealTimeUs2;
                this.mLastSyncInterpolationTimeUs = currentRealTimeUs;
            }
            return this.mStartPresentationTimeUs + (currentRealTimeUs2 - this.mStartActualTimeUs);
        }
    }

    public boolean hasActualTimeSourceStartedTicking() {
        if (this.mStartPresentationTimeUs == -1) {
            return false;
        }
        return this.mActualTimeSource.hasStartedTicking();
    }
}
