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
            long j = this.mScheduledUptimeMillis;
            if (j != 0) {
                long j2 = this.mIntervalMillis;
                if (jUptimeMillis > j + j2) {
                    this.mScheduledUptimeMillis = jUptimeMillis;
                } else {
                    this.mScheduledUptimeMillis = j + j2;
                }
            } else {
                this.mScheduledUptimeMillis = jUptimeMillis;
            }
            this.mHandler.postAtTime(this.mRunnable, this.mScheduledUptimeMillis);
        }
    }
}
