package com.amazon.avod.drm.autorecovery;

import com.amazon.avod.drm.autorecovery.DrmAutoRecoveryPolicy;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.util.SlidingWindowEventTracker;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
class DrmAutoRecoveryExceptionTracker {
    private final DrmAutoRecoveryPolicy.AutoResetConfig mAutoResetConfig;
    private final SlidingWindowEventTracker mExceptionWindow;
    private final Object mWindowStateMutex;

    public DrmAutoRecoveryExceptionTracker() {
        this(DrmAutoRecoveryPolicy.AutoResetConfig.getInstance());
    }

    private DrmAutoRecoveryExceptionTracker(DrmAutoRecoveryPolicy.AutoResetConfig autoResetConfig) {
        this(autoResetConfig, new SlidingWindowEventTracker(autoResetConfig));
    }

    DrmAutoRecoveryExceptionTracker(DrmAutoRecoveryPolicy.AutoResetConfig autoResetConfig, SlidingWindowEventTracker slidingWindowEventTracker) {
        this.mWindowStateMutex = new Object();
        this.mAutoResetConfig = (DrmAutoRecoveryPolicy.AutoResetConfig) Preconditions.checkNotNull(autoResetConfig, "autoResetConfig");
        this.mExceptionWindow = (SlidingWindowEventTracker) Preconditions.checkNotNull(slidingWindowEventTracker, "slidingWindowEventTracker");
    }

    protected boolean isTracked(@Nonnull MediaException mediaException) {
        Preconditions.checkNotNull(mediaException, "exception");
        return this.mAutoResetConfig.getDrmAutoRecoveryErrorConfig().contains(Integer.valueOf(mediaException.getIntegerErrorCode()));
    }

    public void reportException(@Nonnull MediaException mediaException) {
        Preconditions.checkNotNull(mediaException, "exception");
        if (isTracked(mediaException)) {
            synchronized (this.mWindowStateMutex) {
                DLog.logf("Tracked event for exception %s for DRM Auto Recovery", mediaException);
                this.mExceptionWindow.recordEvent(TimeSpan.now());
            }
        }
    }

    public boolean isThresholdBreached() {
        boolean zIsEventCountGreaterThanOrEqualToThreshold;
        synchronized (this.mWindowStateMutex) {
            zIsEventCountGreaterThanOrEqualToThreshold = this.mExceptionWindow.isEventCountGreaterThanOrEqualToThreshold();
        }
        return zIsEventCountGreaterThanOrEqualToThreshold;
    }
}
