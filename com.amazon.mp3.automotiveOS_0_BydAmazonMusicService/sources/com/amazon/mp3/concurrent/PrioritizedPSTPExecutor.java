package com.amazon.mp3.concurrent;

import com.amazon.mp3.util.Log;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class PrioritizedPSTPExecutor extends PausableScheduledThreadPoolExecutor {
    private static final String TAG = PrioritizedPSTPExecutor.class.getSimpleName();
    private PausableScheduledThreadPoolExecutor mPreemptExecutor;

    public PrioritizedPSTPExecutor(int maximumPoolSize) {
        super(maximumPoolSize);
        this.mPreemptExecutor = new PausableScheduledThreadPoolExecutor(1);
    }

    public void schedulePreempt(Runnable command, long delay, TimeUnit unit) {
        if (command == null || unit == null) {
            throw null;
        }
        Log.debug(TAG, "scheduling preempt task to run in %d %s", Long.valueOf(delay), unit);
        this.mPreemptExecutor.schedule(command, delay, unit);
    }

    @Override // java.util.concurrent.ScheduledThreadPoolExecutor, java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        this.mPreemptExecutor.shutdownNow();
        return super.shutdownNow();
    }

    @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.ExecutorService
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        this.mPreemptExecutor.awaitTermination(timeout, unit);
        return super.awaitTermination(timeout, unit);
    }
}
