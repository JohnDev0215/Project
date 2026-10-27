package com.amazon.alexa.mobilytics.event.operational;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultMobilyticsMetricsTimer extends DefaultMobilyticsOperationalEvent implements MobilyticsMetricsTimer {
    private transient long elapsedTime;
    private transient long finishedTime;
    protected transient boolean isRunning;
    private transient long lastStartTime;
    private final transient ReentrantReadWriteLock lock;
    private final transient ReentrantReadWriteLock.ReadLock readLock;
    private final transient ReentrantReadWriteLock.WriteLock writeLock;

    public DefaultMobilyticsMetricsTimer(String str, String str2, String str3, long j, boolean z, String str4) {
        super(str, "timer", str2, str3, str4);
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.lock = reentrantReadWriteLock;
        this.readLock = reentrantReadWriteLock.readLock();
        this.writeLock = reentrantReadWriteLock.writeLock();
        init(z);
        this.elapsedTime = j;
        withEventNumericValue(Long.valueOf(j));
    }

    private void init(boolean z) {
        this.isRunning = z;
        this.lastStartTime = this.eventTimestamp;
        this.elapsedTime = 0L;
    }

    @Override // com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer
    public void finishTimer() {
        this.writeLock.lock();
        try {
            if (this.isRunning) {
                this.isRunning = false;
                long jCurrentTimeMillis = System.currentTimeMillis();
                this.finishedTime = jCurrentTimeMillis;
                this.elapsedTime += jCurrentTimeMillis - this.lastStartTime;
            }
            withEventNumericValue(Long.valueOf(this.elapsedTime));
        } finally {
            this.writeLock.unlock();
        }
    }

    @Override // com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer
    public long getElapsedTime() {
        this.readLock.lock();
        try {
            long j = this.elapsedTime;
            withEventNumericValue(Long.valueOf(j));
            return j;
        } finally {
            this.readLock.unlock();
        }
    }
}
