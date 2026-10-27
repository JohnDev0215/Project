package com.amazon.weblab.mobile;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes5.dex */
class MobileWeblabFailedImmediateFuture<E> implements Future<E> {
    private final Exception mException;

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return true;
    }

    public MobileWeblabFailedImmediateFuture(Exception exc) {
        this.mException = exc;
    }

    @Override // java.util.concurrent.Future
    public E get() throws ExecutionException, InterruptedException {
        throw new ExecutionException(this.mException);
    }

    @Override // java.util.concurrent.Future
    public E get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        throw new ExecutionException(this.mException);
    }
}
