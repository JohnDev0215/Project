package com.amazon.coral.retry;

/* JADX INFO: loaded from: classes2.dex */
public interface CallListener {
    void onAttemptBegin(RetryContext retryContext);

    void onSuccess(RetryContext retryContext);
}
