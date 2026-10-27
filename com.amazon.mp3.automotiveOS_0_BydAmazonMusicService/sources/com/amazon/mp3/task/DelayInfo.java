package com.amazon.mp3.task;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public class DelayInfo {
    long mDelay;
    TimeUnit mTimeUnit;

    public long getDelay() {
        return this.mDelay;
    }

    public void setDelay(long delay) {
        this.mDelay = delay;
    }

    public TimeUnit getTimeUnit() {
        return this.mTimeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.mTimeUnit = timeUnit;
    }
}
