package com.amazon.alexa.voice.core.schedulers;

import com.amazon.alexa.voice.core.Scheduler;
import com.amazon.alexa.voice.core.internal.util.Preconditions;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public final class SingleThreadScheduler implements Scheduler {
    private final Executor executor = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new DefaultThreadFactory());

    @Override // com.amazon.alexa.voice.core.Scheduler
    public void schedule(Runnable runnable) {
        Preconditions.notNull(runnable, "runnable = null");
        this.executor.execute(runnable);
    }

    static class DefaultThreadFactory implements ThreadFactory {
        DefaultThreadFactory() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setDaemon(false);
            return thread;
        }
    }
}
