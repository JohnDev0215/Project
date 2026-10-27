package com.google.common.util.concurrent;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes6.dex */
public interface ListeningScheduledExecutorService extends ScheduledExecutorService, ListeningExecutorService {
    @Override // java.util.concurrent.ScheduledExecutorService
    ListenableScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit);
}
