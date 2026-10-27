package com.amazon.mp3.task;

/* JADX INFO: loaded from: classes4.dex */
public interface RetryStrategy {
    DelayInfo getRetryDelay(long runCount);
}
