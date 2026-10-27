package android.net.util;

import android.os.SystemClock;

/* JADX INFO: loaded from: classes.dex */
public class Stopwatch {
    private long mStartTimeNs;
    private long mStopTimeNs;

    public boolean isStarted() {
        return this.mStartTimeNs > 0;
    }

    public boolean isStopped() {
        return this.mStopTimeNs > 0;
    }

    public boolean isRunning() {
        return isStarted() && !isStopped();
    }

    public Stopwatch start() {
        if (!isStarted()) {
            this.mStartTimeNs = SystemClock.elapsedRealtimeNanos();
        }
        return this;
    }

    public Stopwatch restart() {
        this.mStartTimeNs = SystemClock.elapsedRealtimeNanos();
        this.mStopTimeNs = 0L;
        return this;
    }

    public long stop() {
        if (isRunning()) {
            this.mStopTimeNs = SystemClock.elapsedRealtimeNanos();
        }
        return (this.mStopTimeNs - this.mStartTimeNs) / 1000;
    }

    public void reset() {
        this.mStartTimeNs = 0L;
        this.mStopTimeNs = 0L;
    }
}
