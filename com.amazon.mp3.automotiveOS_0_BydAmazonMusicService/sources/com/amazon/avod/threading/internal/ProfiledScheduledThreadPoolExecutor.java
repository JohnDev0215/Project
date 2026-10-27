package com.amazon.avod.threading.internal;

import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.google.common.base.Preconditions;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ProfiledScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {
    private final boolean mShouldPerformTracing;
    private final ThreadLocal<TraceKey> mTraceKeys;
    private final Profiler.TraceLevel mTraceLevel;
    private final String mTraceName;

    public ProfiledScheduledThreadPoolExecutor(@Nonnegative int i, @Nonnull ThreadFactory threadFactory, @Nonnull String str, @Nonnull Profiler.TraceLevel traceLevel, boolean z) {
        super(i, threadFactory);
        this.mTraceKeys = new ThreadLocal<>();
        this.mTraceName = (String) Preconditions.checkNotNull(str, "traceName");
        this.mTraceLevel = (Profiler.TraceLevel) Preconditions.checkNotNull(traceLevel, "traceLevel");
        this.mShouldPerformTracing = z;
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    protected void beforeExecute(Thread thread, Runnable runnable) {
        if (this.mShouldPerformTracing) {
            this.mTraceKeys.set(Profiler.beginTrace(this.mTraceLevel, this.mTraceName));
        }
        super.beforeExecute(thread, runnable);
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    protected void afterExecute(Runnable runnable, Throwable th) {
        super.afterExecute(runnable, th);
        if (this.mShouldPerformTracing) {
            Profiler.endTrace(this.mTraceKeys.get());
        }
        BackgroundExceptionProcessor.handleBackgroundExceptionIfNecessary(runnable);
    }
}
