package com.amazonaws.mobileconnectors.remoteconfiguration.internal;

import android.os.SystemClock;
import androidx.work.PeriodicWorkRequest;

/* JADX INFO: loaded from: classes5.dex */
public final class ArcusThrottler {
    public static final int CAUSE_FAILED_SYNC = 20;
    public static final int CAUSE_NONE = 0;
    public static final int CAUSE_SUCCESSFUL_SYNC = 10;
    public static final int CAUSE_THROTTLED_SYNC = 30;
    protected static final long DEFAULT_SYNC_JITTER_IN_MS = 5000;
    protected static final long DEFAULT_TIME_BETWEEN_SYNCS_IN_MS = 900000;
    protected static final long MAX_EXP_BACKOFF_WINDOW_SIZE_IN_MS = 900000;
    private static final long ONE_SECOND_MS = 1000;
    private int mCause = 0;
    private long mNextSyncAttemptTime;
    private int mSyncAttempts;

    protected long getCurrentWindowSizeInMS() {
        long j = ONE_SECOND_MS << (this.mSyncAttempts + 1);
        return (j <= 0 || j > PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS) ? PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS : j;
    }

    protected int getSyncAttempts() {
        return this.mSyncAttempts;
    }

    protected long getRandomTimeInsideCurrentWindowInMS() {
        return (long) (Math.random() * getCurrentWindowSizeInMS());
    }

    protected long getJitterInMS() {
        return (long) (Math.random() * 5000.0d);
    }

    public void updateSyncTimeAfterThrottle(long j) {
        if (j <= 0) {
            j = 900000;
        }
        this.mSyncAttempts = 0;
        this.mNextSyncAttemptTime = SystemClock.elapsedRealtime() + Math.min(j, PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS) + getJitterInMS();
        this.mCause = 30;
    }

    public void updateSyncTimeAfterFailure() {
        if (getCurrentWindowSizeInMS() < PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS) {
            this.mSyncAttempts++;
        }
        this.mNextSyncAttemptTime = SystemClock.elapsedRealtime() + getRandomTimeInsideCurrentWindowInMS();
        this.mCause = 20;
    }

    public void updateSyncTimeAfterSuccess() {
        this.mSyncAttempts = 0;
        this.mNextSyncAttemptTime = SystemClock.elapsedRealtime() + PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS;
        this.mCause = 10;
    }

    public long getTimeToNextSyncInMS() {
        return Math.max(0L, this.mNextSyncAttemptTime - SystemClock.elapsedRealtime());
    }

    public boolean isSyncAllowedRightNow() {
        return getTimeToNextSyncInMS() == 0;
    }

    public int getCause() {
        return this.mCause;
    }
}
