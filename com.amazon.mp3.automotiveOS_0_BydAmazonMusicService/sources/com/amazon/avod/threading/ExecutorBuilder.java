package com.amazon.avod.threading;

import com.amazon.avod.perf.Profiler;
import com.amazon.avod.threading.internal.NamedThreadFactory;
import com.amazon.avod.threading.internal.ProfiledThreadPoolExecutor;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.amplifyframework.core.model.ModelIdentifier;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class ExecutorBuilder {
    private static boolean sShouldRunSynchronously;
    private ExecutorHook mExecutorHook;
    private final String mName;
    private Profiler.TraceLevel mTraceLevel = Profiler.TraceLevel.INFO;
    private WorkerQueueType mWorkerQueueType = WorkerQueueType.FIFO;
    private int mThreadPoolSize = 1;
    private boolean mAllowCoreThreadExpiry = false;
    private long mCoreThreadExpiryTime = 0;
    private TimeUnit mCoreThreadExpiryTimeUnit = TimeUnit.SECONDS;
    private boolean mIsVerboseTaskLoggingEnabled = false;
    private boolean mTraceRunnableNames = false;
    private int mQueueCapacity = 0;
    private boolean mIncludeQueueCapacity = false;
    private RejectedExecutionHandler mRejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();

    public enum WorkerQueueType {
        FIFO { // from class: com.amazon.avod.threading.ExecutorBuilder.WorkerQueueType.1
            @Override // com.amazon.avod.threading.ExecutorBuilder.WorkerQueueType
            @Nonnull
            BlockingQueue<Runnable> newQueue() {
                return new LinkedBlockingQueue();
            }

            @Override // com.amazon.avod.threading.ExecutorBuilder.WorkerQueueType
            @Nonnull
            BlockingQueue<Runnable> newQueue(int i) {
                return new LinkedBlockingQueue(i);
            }
        },
        PRIORITY_BASED_FIFO { // from class: com.amazon.avod.threading.ExecutorBuilder.WorkerQueueType.2
            @Override // com.amazon.avod.threading.ExecutorBuilder.WorkerQueueType
            @Nonnull
            BlockingQueue<Runnable> newQueue() {
                return new PriorityBlockingQueue();
            }

            @Override // com.amazon.avod.threading.ExecutorBuilder.WorkerQueueType
            @Nonnull
            BlockingQueue<Runnable> newQueue(int i) {
                return new PriorityBlockingQueue(i);
            }
        };

        @Nonnull
        abstract BlockingQueue<Runnable> newQueue();

        @Nonnull
        abstract BlockingQueue<Runnable> newQueue(int i);
    }

    public static ExecutorBuilder newBuilder(@Nonnull String str, @Nullable String... strArr) {
        Preconditions.checkNotNull(str, "name");
        if (strArr == null || strArr.length == 0) {
            return new ExecutorBuilder(str);
        }
        StringBuilder sb = new StringBuilder(str);
        for (String str2 : strArr) {
            sb.append(":");
            sb.append(str2);
        }
        return new ExecutorBuilder(sb.toString());
    }

    public static ExecutorBuilder newBuilderFor(@Nonnull Object obj, @Nullable String... strArr) {
        Preconditions.checkNotNull(obj, "object");
        if (obj instanceof Class) {
            return newBuilder(((Class) obj).getSimpleName(), strArr);
        }
        return newBuilder(obj.getClass().getSimpleName(), strArr);
    }

    private ExecutorBuilder(@Nonnull String str) {
        this.mName = (String) Preconditions.checkNotNull(str, "name");
    }

    public ExecutorBuilder withProfilerTraceLevel(@Nonnull Profiler.TraceLevel traceLevel) {
        this.mTraceLevel = (Profiler.TraceLevel) Preconditions.checkNotNull(traceLevel, "traceLevel");
        return this;
    }

    public ExecutorBuilder withFixedThreadPoolSize(@Nonnegative int i) {
        Preconditions.checkArgument(i > 0, "threadPoolSize <= 0 (%s)", i);
        this.mThreadPoolSize = i;
        return this;
    }

    public ExecutorBuilder allowCoreThreadExpiry() {
        return allowCoreThreadExpiry(60L, TimeUnit.SECONDS);
    }

    public ExecutorBuilder allowCoreThreadExpiry(@Nonnegative long j, @Nonnull TimeUnit timeUnit) {
        Preconditions.checkArgument(j >= 0, "time < 0 (%s)", j);
        Preconditions.checkNotNull(timeUnit, "timeUnit");
        this.mAllowCoreThreadExpiry = true;
        this.mCoreThreadExpiryTime = j;
        this.mCoreThreadExpiryTimeUnit = timeUnit;
        return this;
    }

    public ExecutorBuilder withWorkerQueueType(@Nonnull WorkerQueueType workerQueueType) {
        this.mWorkerQueueType = (WorkerQueueType) Preconditions.checkNotNull(workerQueueType, "workerQueueType");
        return this;
    }

    public ExecutorBuilder withRunnableNameTracing() {
        this.mTraceRunnableNames = true;
        return this;
    }

    public ExecutorService buildTestFriendly() {
        if (sShouldRunSynchronously) {
            return MoreExecutors.newDirectExecutorService();
        }
        return build();
    }

    public ThreadPoolExecutor build() {
        BlockingQueue<Runnable> blockingQueueNewQueue;
        ThreadFactory threadFactoryNewThreadFactory = NamedThreadFactory.newThreadFactory(this.mName);
        if (this.mIncludeQueueCapacity) {
            blockingQueueNewQueue = this.mWorkerQueueType.newQueue(this.mQueueCapacity);
        } else {
            blockingQueueNewQueue = this.mWorkerQueueType.newQueue();
        }
        int i = this.mThreadPoolSize;
        ProfiledThreadPoolExecutor profiledThreadPoolExecutor = new ProfiledThreadPoolExecutor(i, i, 0L, TimeUnit.MILLISECONDS, blockingQueueNewQueue, threadFactoryNewThreadFactory, this.mRejectedExecutionHandler, convertThreadNameToProfileTraceKey(this.mName), this.mTraceLevel, this.mIsVerboseTaskLoggingEnabled, Profiler.isIsFileLoggingEnabled(), this.mTraceRunnableNames, this.mExecutorHook);
        if (this.mAllowCoreThreadExpiry) {
            profiledThreadPoolExecutor.setKeepAliveTime(this.mCoreThreadExpiryTime, this.mCoreThreadExpiryTimeUnit);
            profiledThreadPoolExecutor.allowCoreThreadTimeOut(true);
        }
        return profiledThreadPoolExecutor;
    }

    @Deprecated
    private static String convertThreadNameToProfileTraceKey(@Nonnull String str) {
        return str.replace(ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR, "").replace(Attributes.PREDEFINED_ATTRIBUTE_PREFIX, ":").replace("-", ":").trim();
    }
}
