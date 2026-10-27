package com.core.system.service.safeguard;

import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import com.core.system.service.safeguard.Watchdog;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class HandlerChecker implements Runnable {
    private Watchdog.Monitor mCurrentMonitor;
    private final Handler mHandler;
    private final String mName;
    private long mStartTime;
    private final long mWaitMax;
    private final ArrayList<Watchdog.Monitor> mMonitors = new ArrayList<>();
    private boolean mCompleted = true;

    public HandlerChecker(Handler handler, String name, long waitMaxMillis) {
        this.mHandler = handler;
        this.mName = name;
        this.mWaitMax = waitMaxMillis;
    }

    public Watchdog.Monitor getCurrentMonitor() {
        return this.mCurrentMonitor;
    }

    public void addMonitor(Watchdog.Monitor monitor) {
        synchronized (this) {
            this.mMonitors.add(monitor);
        }
    }

    public void scheduleCheckLocked() {
        if (!this.mCompleted) {
            return;
        }
        this.mCompleted = false;
        this.mCurrentMonitor = null;
        this.mStartTime = SystemClock.uptimeMillis();
        this.mHandler.postAtFrontOfQueue(this);
    }

    public boolean isOverdueLocked() {
        return !this.mCompleted && SystemClock.uptimeMillis() > this.mStartTime + this.mWaitMax;
    }

    public int getCompletionStateLocked() {
        if (this.mCompleted) {
            return 0;
        }
        long latency = SystemClock.uptimeMillis() - this.mStartTime;
        long j = this.mWaitMax;
        if (latency < j / 2) {
            return 1;
        }
        if (latency < j) {
            return 2;
        }
        return 3;
    }

    public Thread getThread() {
        return this.mHandler.getLooper().getThread();
    }

    public String getName() {
        return this.mName;
    }

    public String describeBlockedStateLocked() {
        if (this.mCurrentMonitor == null) {
            return "Blocked in handler on " + this.mName + " (" + getThread().getName() + ")";
        }
        return "Blocked in monitor " + this.mCurrentMonitor.getClass().getName() + " on " + this.mName + " (" + getThread().getName() + ")";
    }

    @Override // java.lang.Runnable
    public void run() {
        Watchdog.Monitor monitor;
        int size = this.mMonitors.size();
        for (int i = 0; i < size; i++) {
            synchronized (this) {
                monitor = this.mMonitors.get(i);
                this.mCurrentMonitor = monitor;
            }
            try {
                monitor.monitor();
            } catch (Throwable ex) {
                Log.w(Watchdog.TAG, ex);
            }
        }
        synchronized (this) {
            this.mCompleted = true;
            this.mCurrentMonitor = null;
        }
    }
}
