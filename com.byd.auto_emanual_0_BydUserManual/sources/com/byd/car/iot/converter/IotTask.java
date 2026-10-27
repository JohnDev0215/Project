package com.byd.car.iot.converter;

import java.util.concurrent.CompletableFuture;

/* JADX INFO: loaded from: classes.dex */
public class IotTask<T> {
    private final CompletableFuture<T> completableFuture;
    private final long timestamp;

    private IotTask(long j, CompletableFuture<T> completableFuture) {
        this.completableFuture = completableFuture;
        this.timestamp = System.currentTimeMillis() + j;
    }

    public static <T> IotTask<T> create(long j) {
        return new IotTask<>(j, new CompletableFuture());
    }

    public static <T> IotTask<T> create(long j, CompletableFuture<T> completableFuture) {
        return new IotTask<>(j, completableFuture);
    }

    public boolean isTimeout() {
        return System.currentTimeMillis() >= this.timestamp;
    }

    public void closeByException(Exception exc) {
        this.completableFuture.completeExceptionally(exc);
    }

    public void finish(T t) {
        this.completableFuture.complete(t);
    }

    public CompletableFuture<T> get() {
        return this.completableFuture;
    }
}
