package com.amazon.mp3.concurrent;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes3.dex */
public class PausableScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {
    private boolean mIsPaused;
    private ReentrantLock mPauseLock;
    private Condition mUnpaused;

    public PausableScheduledThreadPoolExecutor(int maximumPoolSize) {
        super(maximumPoolSize);
        ReentrantLock reentrantLock = new ReentrantLock();
        this.mPauseLock = reentrantLock;
        this.mUnpaused = reentrantLock.newCondition();
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    protected void beforeExecute(Thread t, Runnable r) {
        this.mPauseLock.lock();
        while (this.mIsPaused) {
            try {
                try {
                    this.mUnpaused.await();
                } catch (InterruptedException unused) {
                    t.interrupt();
                }
            } finally {
                this.mPauseLock.unlock();
            }
        }
        super.beforeExecute(t, r);
    }
}
