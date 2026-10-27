package com.byd.music.utils.task.pool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes5.dex */
public class TaskThreadFactory implements ThreadFactory {
    private final String executorName;
    private final AtomicInteger mCount = new AtomicInteger(1);

    public TaskThreadFactory(String str) {
        this.executorName = str;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, this.executorName + " Executor#" + this.mCount.getAndIncrement());
    }
}
