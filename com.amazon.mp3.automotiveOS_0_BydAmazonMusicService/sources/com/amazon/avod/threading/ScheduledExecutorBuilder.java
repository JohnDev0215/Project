package com.amazon.avod.threading;

import com.amazon.avod.perf.Profiler;
import com.amazon.avod.threading.internal.NamedThreadFactory;
import com.amazon.avod.threading.internal.ProfiledScheduledThreadPoolExecutor;
import com.amazon.avod.util.Preconditions2;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ScheduledExecutorBuilder {
    private final String mName;
    private RejectedExecutionHandler mRejectedExecutionHandler;
    private Profiler.TraceLevel mTraceLevel = Profiler.TraceLevel.INFO;
    private boolean mAllowCoreThreadExpiry = false;
    private long mCoreThreadExpiryTimeQuantity = 0;
    private TimeUnit mCoreThreadExpiryTimeUnit = TimeUnit.SECONDS;
    private int mThreadPoolSize = 1;
    private int mThreadPriority = 5;

    public static ScheduledExecutorBuilder newBuilder(@Nonnull String str, @Nullable String... strArr) {
        Preconditions.checkNotNull(str, "name");
        if (strArr == null || strArr.length == 0) {
            return new ScheduledExecutorBuilder(str);
        }
        StringBuilder sb = new StringBuilder(str);
        for (String str2 : strArr) {
            sb.append(":");
            sb.append(str2);
        }
        return new ScheduledExecutorBuilder(sb.toString());
    }

    public static ScheduledExecutorBuilder newBuilderFor(@Nonnull Object obj, @Nullable String... strArr) {
        Preconditions.checkNotNull(obj, "object");
        if (obj instanceof Class) {
            return newBuilder(((Class) obj).getSimpleName(), strArr);
        }
        return newBuilder(obj.getClass().getSimpleName(), strArr);
    }

    private ScheduledExecutorBuilder(@Nonnull String str) {
        this.mName = (String) Preconditions.checkNotNull(str, "name");
    }

    public ScheduledExecutorBuilder withFixedThreadPoolSize(@Nonnegative int i) {
        Preconditions.checkArgument(i > 0, "threadPoolSize <= 0 (%s)", i);
        this.mThreadPoolSize = i;
        return this;
    }

    public ScheduledExecutorBuilder withThreadPriority(int i) {
        Preconditions.checkArgument(i > 0, "threadPriority <=0 (%s)", i);
        this.mThreadPriority = i;
        return this;
    }

    public ScheduledExecutorBuilder withProfilerTraceLevel(@Nonnull Profiler.TraceLevel traceLevel) {
        this.mTraceLevel = (Profiler.TraceLevel) Preconditions.checkNotNull(traceLevel, "traceLevel");
        return this;
    }

    public ScheduledExecutorBuilder withRejectedExecutionHandler(@Nonnull RejectedExecutionHandler rejectedExecutionHandler) {
        this.mRejectedExecutionHandler = (RejectedExecutionHandler) Preconditions.checkNotNull(rejectedExecutionHandler, "traceLevel");
        return this;
    }

    public ScheduledExecutorBuilder allowCoreThreadExpiry(@Nonnegative long j, @Nonnull TimeUnit timeUnit) {
        this.mCoreThreadExpiryTimeQuantity = Preconditions2.checkNonNegative(j, "time");
        this.mCoreThreadExpiryTimeUnit = (TimeUnit) Preconditions.checkNotNull(timeUnit, "timeUnit");
        this.mAllowCoreThreadExpiry = true;
        return this;
    }

    public ScheduledThreadPoolExecutor build() {
        ProfiledScheduledThreadPoolExecutor profiledScheduledThreadPoolExecutor = new ProfiledScheduledThreadPoolExecutor(this.mThreadPoolSize, NamedThreadFactory.newThreadFactory(this.mName, this.mThreadPriority), this.mName, this.mTraceLevel, Profiler.isIsFileLoggingEnabled());
        if (this.mAllowCoreThreadExpiry) {
            profiledScheduledThreadPoolExecutor.setKeepAliveTime(this.mCoreThreadExpiryTimeQuantity, this.mCoreThreadExpiryTimeUnit);
            profiledScheduledThreadPoolExecutor.allowCoreThreadTimeOut(true);
        }
        RejectedExecutionHandler rejectedExecutionHandler = this.mRejectedExecutionHandler;
        if (rejectedExecutionHandler != null) {
            profiledScheduledThreadPoolExecutor.setRejectedExecutionHandler(rejectedExecutionHandler);
        }
        return profiledScheduledThreadPoolExecutor;
    }

    public ListeningScheduledThreadPoolExecutor buildListeningExecutor() {
        return new ListeningScheduledThreadPoolExecutor(MoreExecutors.listeningDecorator((ScheduledExecutorService) build()));
    }
}
