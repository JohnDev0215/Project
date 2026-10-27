package com.byd.music.utils.task.pool;

import android.car.trust.TrustedDeviceInfo;
import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public class TaskPriorityExecutor implements Executor {
    private static final int CORE_POOL_SIZE;
    private static final int CPU_COUNT;
    private static final int KEEP_ALIVE = 30;
    private static final int MAXIMUM_POOL_SIZE;
    private static final Comparator<Runnable> RUNNABLE_COMPARATOR;
    private final BlockingQueue<Runnable> mPoolWorkQueue;
    private final ThreadPoolExecutor mThreadPoolExecutor;

    static {
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        CPU_COUNT = iAvailableProcessors;
        CORE_POOL_SIZE = Math.max(3, Math.min(iAvailableProcessors - 1, 4));
        MAXIMUM_POOL_SIZE = (CPU_COUNT * 2) + 1;
        RUNNABLE_COMPARATOR = new Comparator<Runnable>() { // from class: com.byd.music.utils.task.pool.TaskPriorityExecutor.1
            @Override // java.util.Comparator
            public int compare(Runnable runnable, Runnable runnable2) {
                if ((runnable instanceof TaskPriorityRunnable) && (runnable2 instanceof TaskPriorityRunnable)) {
                    return ((TaskPriorityRunnable) runnable).priority.ordinal() - ((TaskPriorityRunnable) runnable2).priority.ordinal();
                }
                return 0;
            }
        };
    }

    public TaskPriorityExecutor() {
        this(CORE_POOL_SIZE, new TaskThreadFactory(TrustedDeviceInfo.DEFAULT_NAME));
    }

    public TaskPriorityExecutor(int i, ThreadFactory threadFactory) {
        this.mPoolWorkQueue = new PriorityBlockingQueue(MAXIMUM_POOL_SIZE, RUNNABLE_COMPARATOR);
        this.mThreadPoolExecutor = new ThreadPoolExecutor(i, MAXIMUM_POOL_SIZE, 30L, TimeUnit.SECONDS, this.mPoolWorkQueue, threadFactory);
    }

    public TaskPriorityExecutor(ThreadFactory threadFactory) {
        this.mPoolWorkQueue = new PriorityBlockingQueue(MAXIMUM_POOL_SIZE, RUNNABLE_COMPARATOR);
        this.mThreadPoolExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), threadFactory);
    }

    public int getPoolSize() {
        return this.mThreadPoolExecutor.getCorePoolSize();
    }

    public void setPoolSize(int i) {
        if (i > 0) {
            this.mThreadPoolExecutor.setCorePoolSize(i);
        }
    }

    public ThreadPoolExecutor getThreadPoolExecutor() {
        return this.mThreadPoolExecutor;
    }

    public boolean isBusy() {
        return this.mThreadPoolExecutor.getActiveCount() >= this.mThreadPoolExecutor.getCorePoolSize();
    }

    public boolean isFull() {
        return this.mThreadPoolExecutor.getActiveCount() >= this.mThreadPoolExecutor.getCorePoolSize() * 2;
    }

    public boolean isShutdown() {
        return this.mThreadPoolExecutor.isShutdown();
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.mThreadPoolExecutor.execute(runnable);
    }
}
