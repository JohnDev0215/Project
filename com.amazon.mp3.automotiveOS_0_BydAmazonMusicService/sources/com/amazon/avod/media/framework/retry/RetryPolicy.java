package com.amazon.avod.media.framework.retry;

/* JADX INFO: loaded from: classes2.dex */
public interface RetryPolicy<T> {
    boolean shouldRetryOnException(Exception exc);

    boolean shouldRetryWithResult(T t);
}
