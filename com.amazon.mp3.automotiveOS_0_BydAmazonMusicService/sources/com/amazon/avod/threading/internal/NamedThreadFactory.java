package com.amazon.avod.threading.internal;

import com.amplifyframework.core.model.ModelIdentifier;
import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class NamedThreadFactory implements ThreadFactory {
    private static final Cache<Thread, String> NAMED_THREADS_CACHE = CacheBuilder.newBuilder().weakKeys().build();
    private final AtomicLong mThreadCount;
    private final ThreadFactory mThreadFactory;
    private final String mThreadName;
    private final int mThreadPriority;

    public static ThreadFactory newThreadFactory(@Nonnull String str) {
        return new NamedThreadFactory(removeUnsafeThreadChars(str));
    }

    public static ThreadFactory newThreadFactory(@Nonnull String str, int i) {
        return new NamedThreadFactory(removeUnsafeThreadChars(str), i);
    }

    @Deprecated
    private static String removeUnsafeThreadChars(@Nonnull String str) {
        return str.replace(ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR, "").replace(" ", "");
    }

    NamedThreadFactory(@Nonnull String str) {
        this(str, Executors.defaultThreadFactory(), 5);
    }

    NamedThreadFactory(@Nonnull String str, int i) {
        this(str, Executors.defaultThreadFactory(), i);
    }

    NamedThreadFactory(@Nonnull String str, @Nonnull ThreadFactory threadFactory, int i) {
        this.mThreadCount = new AtomicLong(0L);
        this.mThreadName = (String) Preconditions.checkNotNull(str, "threadName");
        Preconditions.checkArgument(i > 0, "threadPriority <=0 (%s)", i);
        this.mThreadPriority = i;
        this.mThreadFactory = (ThreadFactory) Preconditions.checkNotNull(threadFactory, "threadFactory");
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread threadNewThread = this.mThreadFactory.newThread(runnable);
        threadNewThread.setName(String.format(Locale.US, "%s-%s", this.mThreadName, Long.valueOf(this.mThreadCount.getAndIncrement())));
        NAMED_THREADS_CACHE.put(threadNewThread, "NotNeeded");
        int i = this.mThreadPriority;
        if (i != 5) {
            threadNewThread.setPriority(i);
        }
        return threadNewThread;
    }

    public static Set<Thread> getAllNamedThreads() {
        return NAMED_THREADS_CACHE.asMap().keySet();
    }
}
