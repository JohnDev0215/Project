package com.facebook.react.common.futures;

import androidx.annotation.Nullable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes6.dex */
public class SimpleSettableFuture<T> implements Future<T> {

    @Nullable
    private Exception mException;
    private final CountDownLatch mReadyLatch = new CountDownLatch(1);

    @Nullable
    private T mResult;

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return false;
    }

    public void set(@Nullable T t) {
        checkNotSet();
        this.mResult = t;
        this.mReadyLatch.countDown();
    }

    public void setException(Exception exc) {
        checkNotSet();
        this.mException = exc;
        this.mReadyLatch.countDown();
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.mReadyLatch.getCount() == 0;
    }

    @Override // java.util.concurrent.Future
    @Nullable
    public T get() throws ExecutionException, InterruptedException {
        this.mReadyLatch.await();
        if (this.mException != null) {
            throw new ExecutionException(this.mException);
        }
        return this.mResult;
    }

    @Override // java.util.concurrent.Future
    @Nullable
    public T get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        if (!this.mReadyLatch.await(j, timeUnit)) {
            throw new TimeoutException("Timed out waiting for result");
        }
        if (this.mException != null) {
            throw new ExecutionException(this.mException);
        }
        return this.mResult;
    }

    @Nullable
    public T getOrThrow() {
        try {
            return get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkNotSet() {
        if (this.mReadyLatch.getCount() == 0) {
            throw new RuntimeException("Result has already been set!");
        }
    }
}
