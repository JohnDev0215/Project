package com.google.common.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes6.dex */
public interface ListeningExecutorService extends ExecutorService {
    ListenableFuture<?> submit(Runnable runnable);

    @Override // java.util.concurrent.ExecutorService, com.google.common.util.concurrent.ListeningExecutorService
    <T> ListenableFuture<T> submit(Callable<T> callable);
}
