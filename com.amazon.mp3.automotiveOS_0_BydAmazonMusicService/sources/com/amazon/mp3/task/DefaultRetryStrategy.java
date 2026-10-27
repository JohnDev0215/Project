package com.amazon.mp3.task;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public class DefaultRetryStrategy implements RetryStrategy {
    private long mInitialDelay = 0;
    private TimeUnit mInitialDelayTimeUnit;
    private long mMaxRetries;
    private long mRetryDelay;
    private TimeUnit mRetryDelayTimeUnit;

    public DefaultRetryStrategy() {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        this.mInitialDelayTimeUnit = timeUnit;
        this.mRetryDelay = 10L;
        this.mRetryDelayTimeUnit = timeUnit;
        this.mMaxRetries = -1L;
    }

    @Override // com.amazon.mp3.task.RetryStrategy
    public DelayInfo getRetryDelay(long runCount) {
        long j = this.mMaxRetries;
        if (j > 0 && runCount > j) {
            return null;
        }
        DelayInfo delayInfo = new DelayInfo();
        if (runCount == 0) {
            delayInfo.setDelay(this.mInitialDelay);
            delayInfo.setTimeUnit(this.mInitialDelayTimeUnit);
        } else {
            delayInfo.setDelay(this.mRetryDelay);
            delayInfo.setTimeUnit(this.mRetryDelayTimeUnit);
        }
        return delayInfo;
    }
}
