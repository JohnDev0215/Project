package com.alibaba.android.arouter.thread;

import androidx.annotation.NonNull;
import com.alibaba.android.arouter.facade.template.ILogger;
import com.alibaba.android.arouter.launcher.ARouter;
import java.lang.Thread;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import spi.c;

/* JADX INFO: loaded from: classes.dex */
public class DefaultThreadFactory implements ThreadFactory {
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final String namePrefix;
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    public class a implements Thread.UncaughtExceptionHandler {
        public a(DefaultThreadFactory defaultThreadFactory) {
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public final void uncaughtException(Thread thread, Throwable th) {
            ILogger iLogger = ARouter.logger;
            StringBuilder sbK = c.k("Running task appeared exception! Thread [");
            sbK.append(thread.getName());
            sbK.append("], because [");
            sbK.append(th.getMessage());
            sbK.append("]");
            iLogger.info("ARouter::", sbK.toString());
        }
    }

    public DefaultThreadFactory() {
        SecurityManager securityManager = System.getSecurityManager();
        this.group = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
        StringBuilder sbK = c.k("ARouter task pool No.");
        sbK.append(poolNumber.getAndIncrement());
        sbK.append(", thread No.");
        this.namePrefix = sbK.toString();
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(@NonNull Runnable runnable) {
        String str = this.namePrefix + this.threadNumber.getAndIncrement();
        ARouter.logger.info("ARouter::", "Thread production, name is [" + str + "]");
        Thread thread = new Thread(this.group, runnable, str, 0L);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        if (thread.getPriority() != 5) {
            thread.setPriority(5);
        }
        thread.setUncaughtExceptionHandler(new a(this));
        return thread;
    }
}
