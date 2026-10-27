package com.byd.foundation.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class DiExecutors {
    public static final int CORE_POOL_SIZE;
    private static final int CPU_COUNT;
    private static final int KEEP_ALIVE_TIME = 5;
    private static final int MAX_POOL_SIZE;
    private static final RejectedExecutionHandler sHandler;
    private static volatile DiExecutors sInstance;
    private ThreadPoolExecutor mCPUExecutor;
    private ExecutorService mIOExecutor;

    static {
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        CPU_COUNT = iAvailableProcessors;
        int iMax = Math.max(2, Math.min(iAvailableProcessors - 1, 5));
        CORE_POOL_SIZE = iMax;
        MAX_POOL_SIZE = iMax;
        sHandler = new RejectedExecutionHandler() { // from class: com.byd.foundation.executor.DiExecutors.1
            @Override // java.util.concurrent.RejectedExecutionHandler
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                Executors.newCachedThreadPool().execute(runnable);
            }
        };
    }

    private DiExecutors() {
        int i = CORE_POOL_SIZE;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(i, MAX_POOL_SIZE, 5L, TimeUnit.SECONDS, new PriorityBlockingQueue(), new DefaultThreadFactory("CPU thread"), sHandler);
        this.mCPUExecutor = threadPoolExecutor;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        this.mIOExecutor = Executors.newFixedThreadPool(i * 2, new DefaultThreadFactory("IO thread"));
    }

    public static DiExecutors get() {
        if (sInstance == null) {
            synchronized (DiExecutors.class) {
                if (sInstance == null) {
                    sInstance = new DiExecutors();
                }
            }
        }
        return sInstance;
    }

    public void execute(Runnable runnable) {
        execute(5, runnable);
    }

    public void execute(int i, Runnable runnable) {
        this.mCPUExecutor.execute(new PriorityRunnable(i, runnable));
    }

    public <T> Future<T> submit(Callable<T> callable) {
        return submit(5, callable);
    }

    public <T> Future<T> submit(int i, Callable<T> callable) {
        return this.mCPUExecutor.submit(new PriorityCallable(i, callable));
    }

    public void executeIoTask(Runnable runnable) {
        executeIoTask(5, runnable);
    }

    public void executeIoTask(int i, Runnable runnable) {
        this.mIOExecutor.execute(new PriorityRunnable(i, runnable));
    }

    public <T> Future<T> submitIoTask(Callable callable) {
        return submitIoTask(5, callable);
    }

    public <T> Future<T> submitIoTask(int i, Callable<T> callable) {
        return this.mIOExecutor.submit(new PriorityCallable(i, callable));
    }

    private static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final String namePrefix;
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        DefaultThreadFactory(String str) {
            SecurityManager securityManager = System.getSecurityManager();
            this.group = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.namePrefix = str + "-" + poolNumber.getAndIncrement();
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.group, runnable, this.namePrefix + this.threadNumber.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            return thread;
        }
    }

    private static class PriorityRunnable implements Runnable {
        private final int priority;
        private final Runnable task;

        public PriorityRunnable(int i, Runnable runnable) {
            this.task = runnable;
            this.priority = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            Thread.currentThread().setPriority(this.priority);
            this.task.run();
        }
    }

    private static class PriorityCallable<T> implements Callable<T> {
        private final int priority;
        private final Callable<T> task;

        public PriorityCallable(int i, Callable<T> callable) {
            this.task = callable;
            this.priority = i;
        }

        @Override // java.util.concurrent.Callable
        public T call() throws Exception {
            Thread.currentThread().setPriority(this.priority);
            return this.task.call();
        }
    }
}
