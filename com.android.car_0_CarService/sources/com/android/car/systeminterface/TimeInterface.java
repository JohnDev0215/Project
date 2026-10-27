package com.android.car.systeminterface;

import android.os.SystemClock;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public interface TimeInterface {
    public static final boolean EXCLUDE_DEEP_SLEEP_TIME = false;
    public static final boolean INCLUDE_DEEP_SLEEP_TIME = true;

    void cancelAllActions();

    void scheduleAction(Runnable runnable, long j);

    default long getUptime() {
        return getUptime(false);
    }

    default long getUptime(boolean includeDeepSleepTime) {
        if (includeDeepSleepTime) {
            return SystemClock.elapsedRealtime();
        }
        return SystemClock.uptimeMillis();
    }

    public static class DefaultImpl implements TimeInterface {
        private ScheduledExecutorService mExecutor;
        private final Object mLock = new Object();

        @Override // com.android.car.systeminterface.TimeInterface
        public void scheduleAction(Runnable r, long delayMs) {
            ScheduledExecutorService executor;
            synchronized (this.mLock) {
                executor = this.mExecutor;
                if (executor == null) {
                    executor = Executors.newSingleThreadScheduledExecutor();
                    this.mExecutor = executor;
                }
            }
            executor.scheduleAtFixedRate(r, delayMs, delayMs, TimeUnit.MILLISECONDS);
        }

        @Override // com.android.car.systeminterface.TimeInterface
        public void cancelAllActions() {
            ScheduledExecutorService executor;
            synchronized (this.mLock) {
                executor = this.mExecutor;
                this.mExecutor = null;
            }
            if (executor != null) {
                executor.shutdownNow();
            }
        }
    }
}
