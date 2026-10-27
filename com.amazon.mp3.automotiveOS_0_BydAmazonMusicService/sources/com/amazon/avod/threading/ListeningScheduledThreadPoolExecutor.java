package com.amazon.avod.threading;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableScheduledFuture;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class ListeningScheduledThreadPoolExecutor {
    final Set<ListenableFuture<?>> mScheduledTasksInFlight = WeakReferenceSet.build();
    private final ListeningScheduledExecutorService mThreadPool;

    ListeningScheduledThreadPoolExecutor(ListeningScheduledExecutorService listeningScheduledExecutorService) {
        this.mThreadPool = listeningScheduledExecutorService;
    }

    public ListenableScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        ListenableScheduledFuture<?> listenableScheduledFutureSchedule = this.mThreadPool.schedule(runnable, j, timeUnit);
        this.mScheduledTasksInFlight.add(listenableScheduledFutureSchedule);
        return listenableScheduledFutureSchedule;
    }
}
