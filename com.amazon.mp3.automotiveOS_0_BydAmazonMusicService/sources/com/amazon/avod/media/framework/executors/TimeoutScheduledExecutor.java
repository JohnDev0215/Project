package com.amazon.avod.media.framework.executors;

import com.amazon.avod.media.framework.executors.TimeoutTask;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class TimeoutScheduledExecutor extends ScheduledThreadPoolExecutor {
    private final List<TimeoutRunnableScheduledFuture<?>> mRunningTimeoutTasks;
    private final ScheduledExecutorService mTimeoutEnforcerExecutor;
    private final Runnable mTimeoutEnforcerRunnable;

    public TimeoutScheduledExecutor(String str, int i, long j, long j2) {
        super(i, new ThreadFactoryBuilder().setNameFormat(str + ".T-%d").build());
        Runnable runnable = new Runnable() { // from class: com.amazon.avod.media.framework.executors.TimeoutScheduledExecutor.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (TimeoutScheduledExecutor.this.mRunningTimeoutTasks) {
                    if (!TimeoutScheduledExecutor.this.isShutdown() && !TimeoutScheduledExecutor.this.mRunningTimeoutTasks.isEmpty()) {
                        Iterator it = TimeoutScheduledExecutor.this.mRunningTimeoutTasks.iterator();
                        while (it.hasNext()) {
                            ((TimeoutRunnableScheduledFuture) it.next()).checkTimeout();
                        }
                    }
                }
            }
        };
        this.mTimeoutEnforcerRunnable = runnable;
        this.mRunningTimeoutTasks = Lists.newLinkedList();
        if (j > 0) {
            setKeepAliveTime(j, TimeUnit.MILLISECONDS);
            allowCoreThreadTimeOut(true);
        }
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutorBuild = ScheduledExecutorBuilder.newBuilder(str + ".TimeoutEnforcerThread", new String[0]).withFixedThreadPoolSize(1).withProfilerTraceLevel(Profiler.TraceLevel.DEBUG).build();
        this.mTimeoutEnforcerExecutor = scheduledThreadPoolExecutorBuild;
        scheduledThreadPoolExecutorBuild.scheduleAtFixedRate(runnable, 0L, j2, TimeUnit.MILLISECONDS);
    }

    @Override // java.util.concurrent.ScheduledThreadPoolExecutor
    protected <T> RunnableScheduledFuture<T> decorateTask(Runnable runnable, RunnableScheduledFuture<T> runnableScheduledFuture) {
        return runnable instanceof TimeoutTask.TimeoutRunnable ? new TimeoutRunnableScheduledFuture((TimeoutTask.TimeoutRunnable) runnable, runnableScheduledFuture) : runnableScheduledFuture;
    }

    @Override // java.util.concurrent.ScheduledThreadPoolExecutor
    protected <T> RunnableScheduledFuture<T> decorateTask(Callable<T> callable, RunnableScheduledFuture<T> runnableScheduledFuture) {
        return callable instanceof TimeoutTask.TimeoutCallable ? new TimeoutRunnableScheduledFuture((TimeoutTask.TimeoutCallable) callable, runnableScheduledFuture) : runnableScheduledFuture;
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    protected void beforeExecute(Thread thread, Runnable runnable) {
        if (runnable instanceof TimeoutRunnableScheduledFuture) {
            synchronized (this.mRunningTimeoutTasks) {
                this.mRunningTimeoutTasks.add((TimeoutRunnableScheduledFuture) runnable);
            }
        }
        super.beforeExecute(thread, runnable);
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    protected void afterExecute(Runnable runnable, Throwable th) {
        super.afterExecute(runnable, th);
        if (runnable instanceof TimeoutRunnableScheduledFuture) {
            synchronized (this.mRunningTimeoutTasks) {
                this.mRunningTimeoutTasks.remove(runnable);
            }
            ((TimeoutRunnableScheduledFuture) runnable).invokePostRun();
        }
    }

    @Override // java.util.concurrent.ScheduledThreadPoolExecutor, java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.ExecutorService
    public void shutdown() {
        synchronized (this.mRunningTimeoutTasks) {
            this.mTimeoutEnforcerExecutor.shutdown();
            super.shutdown();
        }
    }

    @Override // java.util.concurrent.ScheduledThreadPoolExecutor, java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.ExecutorService
    @Nonnull
    public List<Runnable> shutdownNow() {
        List<Runnable> listShutdownNow;
        synchronized (this.mRunningTimeoutTasks) {
            this.mTimeoutEnforcerExecutor.shutdownNow();
            listShutdownNow = super.shutdownNow();
        }
        return listShutdownNow;
    }
}
