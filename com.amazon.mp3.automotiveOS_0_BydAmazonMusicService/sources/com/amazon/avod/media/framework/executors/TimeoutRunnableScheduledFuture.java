package com.amazon.avod.media.framework.executors;

import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.google.common.base.Objects;
import com.google.common.base.Stopwatch;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes2.dex */
class TimeoutRunnableScheduledFuture<T> implements RunnableScheduledFuture<T> {
    private final RunnableScheduledFuture<T> mDecoratedTask;
    private final TimeoutTask mTimeoutTask;
    private final AtomicLong mTimeoutMillis = new AtomicLong(-1);
    private final Stopwatch mStopwatch = Stopwatch.createUnstarted(Tickers.androidTicker());

    public TimeoutRunnableScheduledFuture(TimeoutTask timeoutTask, RunnableScheduledFuture<T> runnableScheduledFuture) {
        this.mTimeoutTask = timeoutTask;
        this.mDecoratedTask = runnableScheduledFuture;
    }

    public long checkTimeout() {
        long jCheckTimeout;
        synchronized (this.mStopwatch) {
            jCheckTimeout = checkTimeout(this.mStopwatch.elapsed(TimeUnit.MILLISECONDS));
        }
        return jCheckTimeout;
    }

    long checkTimeout(long j) {
        long millis = TimeUnit.NANOSECONDS.toMillis(this.mTimeoutTask.getTimeoutInNanos()) - j;
        if (millis <= 0 && this.mTimeoutMillis.compareAndSet(-1L, j)) {
            cancel(true);
        }
        return Math.max(millis, 0L);
    }

    public void invokePostRun() {
        this.mTimeoutTask.postRun();
    }

    @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
    public void run() {
        synchronized (this.mStopwatch) {
            this.mStopwatch.start();
        }
        this.mDecoratedTask.run();
        synchronized (this.mStopwatch) {
            this.mStopwatch.stop();
        }
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        boolean zCancel = this.mDecoratedTask.cancel(z);
        this.mTimeoutTask.onCancel();
        return zCancel;
    }

    @Override // java.util.concurrent.Future
    public T get() throws ExecutionException, InterruptedException {
        try {
            return this.mDecoratedTask.get();
        } catch (InterruptedException | CancellationException e) {
            throwIfTimedOut();
            throw e;
        }
    }

    @Override // java.util.concurrent.Future
    public T get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        try {
            return this.mDecoratedTask.get(j, timeUnit);
        } catch (InterruptedException | CancellationException e) {
            throwIfTimedOut();
            throw e;
        }
    }

    private void throwIfTimedOut() throws ExecutionException {
        if (this.mTimeoutMillis.get() == -1) {
            return;
        }
        DLog.warnf("Request timed out after %s ms", Long.valueOf(this.mTimeoutMillis.get()));
        throw new ExecutionException(new TimeoutException(String.format(Locale.US, "Timed out after %s ms", Long.valueOf(this.mTimeoutMillis.get()))));
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.mDecoratedTask.isCancelled();
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.mDecoratedTask.isDone();
    }

    @Override // java.util.concurrent.Delayed
    public long getDelay(TimeUnit timeUnit) {
        return this.mDecoratedTask.getDelay(timeUnit);
    }

    @Override // java.lang.Comparable
    public int compareTo(Delayed delayed) {
        return this.mDecoratedTask.compareTo(delayed);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Delayed)) {
            return false;
        }
        return this.mDecoratedTask.equals((Delayed) obj);
    }

    public int hashCode() {
        return Objects.hashCode(this.mDecoratedTask);
    }

    @Override // java.util.concurrent.RunnableScheduledFuture
    public boolean isPeriodic() {
        return this.mDecoratedTask.isPeriodic();
    }
}
