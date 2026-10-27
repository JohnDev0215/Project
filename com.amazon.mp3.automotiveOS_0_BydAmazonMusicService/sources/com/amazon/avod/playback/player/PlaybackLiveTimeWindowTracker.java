package com.amazon.avod.playback.player;

import com.amazon.avod.util.ExponentialBackoff;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackLiveTimeWindowTracker {
    private long mCurrentLiveWindowsStartTimeThreshold;
    private final ExponentialBackoff mExponentialBackoff;
    private final boolean mIsExponentialTimeWindowStartThresholdEnabled;
    private final long mTimeWindowStartThreshold;

    public PlaybackLiveTimeWindowTracker(@Nonnull PlaybackConfig playbackConfig) {
        this(playbackConfig.isExponentialTimeWindowStartThresholdEnabled(), playbackConfig.getTimeWindowStartThreshold().getTotalMilliseconds(), new ExponentialBackoff(playbackConfig.getTimeWindowStartThreshold().getTotalMilliseconds(), playbackConfig.getTimeWindowStartMaxThreshold().getTotalMilliseconds(), playbackConfig.getExponentialTimeWindowStartFactor()));
    }

    PlaybackLiveTimeWindowTracker(boolean z, long j, @Nonnull ExponentialBackoff exponentialBackoff) {
        this.mIsExponentialTimeWindowStartThresholdEnabled = z;
        this.mTimeWindowStartThreshold = j;
        this.mCurrentLiveWindowsStartTimeThreshold = j;
        this.mExponentialBackoff = (ExponentialBackoff) Preconditions.checkNotNull(exponentialBackoff, "exponentialBackoff");
    }

    public void disableCurrentSessionTimeWindowStartThreshold() {
        this.mExponentialBackoff.reset();
    }

    public long getCurrentLiveWindowStartThreshold() {
        return this.mCurrentLiveWindowsStartTimeThreshold;
    }

    public long getTimeWindowStartThreshold() {
        if (!this.mIsExponentialTimeWindowStartThresholdEnabled) {
            return this.mTimeWindowStartThreshold;
        }
        long jNextBackoffMillis = this.mExponentialBackoff.nextBackoffMillis();
        this.mCurrentLiveWindowsStartTimeThreshold = jNextBackoffMillis;
        return jNextBackoffMillis;
    }
}
