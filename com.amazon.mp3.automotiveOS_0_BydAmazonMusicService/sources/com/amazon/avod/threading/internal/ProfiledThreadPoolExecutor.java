package com.amazon.avod.threading.internal;

import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.threading.ExecutorHook;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ProfiledThreadPoolExecutor extends ThreadPoolExecutor {

    @Nullable
    private final ExecutorHook mExecutorHook;
    private final boolean mIncludeRunnableToStringInTraces;
    private final boolean mIncludeVerboseLogging;
    private final boolean mShouldPerformTracing;
    private final ThreadLocal<TraceKey> mTraceKeys;
    private final Profiler.TraceLevel mTraceLevel;
    private final String mTraceName;

    public ProfiledThreadPoolExecutor(@Nonnegative int i, @Nonnegative int i2, @Nonnegative long j, @Nonnull TimeUnit timeUnit, @Nonnull BlockingQueue<Runnable> blockingQueue, @Nonnull ThreadFactory threadFactory, @Nonnull RejectedExecutionHandler rejectedExecutionHandler, @Nonnull String str, @Nonnull Profiler.TraceLevel traceLevel, boolean z, boolean z2, boolean z3, @Nullable ExecutorHook executorHook) {
        super(i, i2, j, timeUnit, blockingQueue, threadFactory, rejectedExecutionHandler);
        this.mTraceKeys = new ThreadLocal<>();
        Preconditions.checkNotNull(str, "traceName");
        this.mTraceLevel = (Profiler.TraceLevel) Preconditions.checkNotNull(traceLevel, "traceLevel");
        this.mIncludeVerboseLogging = z;
        this.mShouldPerformTracing = z2;
        this.mIncludeRunnableToStringInTraces = z3;
        this.mExecutorHook = executorHook;
        if (z3) {
            str = str + ":%s";
        }
        this.mTraceName = str;
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    protected void beforeExecute(Thread thread, Runnable runnable) {
        if (this.mShouldPerformTracing) {
            if (this.mIncludeRunnableToStringInTraces) {
                this.mTraceKeys.set(Profiler.beginTrace(this.mTraceLevel, this.mTraceName, runnable));
            } else {
                this.mTraceKeys.set(Profiler.beginTrace(this.mTraceLevel, this.mTraceName));
            }
        }
        ExecutorHook executorHook = this.mExecutorHook;
        if (executorHook != null) {
            executorHook.onBeforeExecute(runnable);
        }
        super.beforeExecute(thread, runnable);
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    protected void afterExecute(Runnable runnable, Throwable th) {
        super.afterExecute(runnable, th);
        ExecutorHook executorHook = this.mExecutorHook;
        if (executorHook != null) {
            executorHook.onAfterExecute(runnable, th);
        }
        if (this.mShouldPerformTracing) {
            Profiler.endTrace(this.mTraceKeys.get());
        }
        BackgroundExceptionProcessor.handleBackgroundExceptionIfNecessary(runnable);
    }

    @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        logCountIfVerboseLoggingEnabled(runnable);
        super.execute(runnable);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Callable<T> callable) {
        logCountIfVerboseLoggingEnabled(callable);
        return super.submit(callable);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public Future<?> submit(Runnable runnable) {
        logCountIfVerboseLoggingEnabled(runnable);
        return super.submit(runnable);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Runnable runnable, T t) {
        logCountIfVerboseLoggingEnabled(runnable);
        return super.submit(runnable, t);
    }

    private void logCountIfVerboseLoggingEnabled(Object obj) {
        if (this.mIncludeVerboseLogging) {
            int activeCount = getActiveCount();
            int size = getQueue().size();
            long completedTaskCount = getCompletedTaskCount();
            long taskCount = getTaskCount();
            if (activeCount + size > getCorePoolSize()) {
                DLog.warnf("Scheduling task [%s] for execution on pool %s: %s running, %s queued (%s of %s completed over lifetime)", obj.toString(), getThreadFactory().toString(), Integer.valueOf(activeCount), Integer.valueOf(size), Long.valueOf(completedTaskCount), Long.valueOf(taskCount));
            } else {
                DLog.devf("Scheduling task [%s] for execution on pool %s: %s running, %s queued (%s of %s completed over lifetime)", obj.toString(), getThreadFactory().toString(), Integer.valueOf(activeCount), Integer.valueOf(size), Long.valueOf(completedTaskCount), Long.valueOf(taskCount));
            }
        }
    }
}
