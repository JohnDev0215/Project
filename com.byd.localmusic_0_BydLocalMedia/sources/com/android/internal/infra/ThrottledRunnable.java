package com.android.internal.infra;

import android.os.Handler;
import android.os.SystemClock;

/* JADX INFO: loaded from: classes4.dex */
public class ThrottledRunnable implements Runnable {
    private final Handler mHandler;
    private final long mIntervalMillis;
    private final Object mLock = new Object();
    private final Runnable mRunnable;
    private long mScheduledUptimeMillis;

    public ThrottledRunnable(Handler handler, long j, Runnable runnable) {
        this.mHandler = handler;
        this.mIntervalMillis = j;
        this.mRunnable = runnable;
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this.mLock) {
            if (this.mHandler.hasCallbacks(this.mRunnable)) {
                return;
            }
            long jUptimeMillis = SystemClock.uptimeMillis();
            if (this.mScheduledUptimeMillis == 0 || jUptimeMillis > this.mScheduledUptimeMillis + this.mIntervalMillis) {
                this.mScheduledUptimeMillis = jUptimeMillis;
            } else {
                this.mScheduledUptimeMillis += this.mIntervalMillis;
            }
            this.mHandler.postAtTime(this.mRunnable, this.mScheduledUptimeMillis);
        }
    }
}
