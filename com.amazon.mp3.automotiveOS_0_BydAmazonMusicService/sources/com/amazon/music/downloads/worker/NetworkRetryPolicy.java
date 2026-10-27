package com.amazon.music.downloads.worker;

/* JADX INFO: loaded from: classes4.dex */
public interface NetworkRetryPolicy {
    boolean sleepBeforeRetry(long j);
}
