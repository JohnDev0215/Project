package com.facebook.common.executors;

import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public abstract class StatefulRunnable<T> implements Runnable {
    protected final AtomicInteger mState = new AtomicInteger(0);

    protected void disposeResult(@Nullable T result) {
    }

    @Nullable
    protected abstract T getResult() throws Exception;

    protected void onCancellation() {
    }

    protected void onFailure(Exception e) {
    }

    protected void onSuccess(@Nullable T result) {
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.mState.compareAndSet(0, 1)) {
            try {
                T result = getResult();
                this.mState.set(3);
                try {
                    onSuccess(result);
                } finally {
                    disposeResult(result);
                }
            } catch (Exception e) {
                this.mState.set(4);
                onFailure(e);
            }
        }
    }

    public void cancel() {
        if (this.mState.compareAndSet(0, 2)) {
            onCancellation();
        }
    }
}
