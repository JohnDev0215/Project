package com.amazon.coral.retry;

/* JADX INFO: loaded from: classes2.dex */
public interface RetryStrategy<CONTEXT> {
    long nextDelayMillis(long j, int i, Exception exc, CONTEXT context);
}
