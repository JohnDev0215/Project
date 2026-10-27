package com.amazon.avod.media.framework.retry;

import com.amazon.avod.media.framework.retry.RetriableCall;

/* JADX INFO: loaded from: classes2.dex */
public interface RetryListener<T> {
    RetriableCall.RetryCode onRetry(T t, Exception exc, int i);
}
