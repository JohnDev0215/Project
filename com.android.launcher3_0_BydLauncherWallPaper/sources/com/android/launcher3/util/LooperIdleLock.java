package com.android.launcher3.util;

import android.os.Looper;
import android.os.MessageQueue;

/* JADX INFO: loaded from: classes4.dex */
public class LooperIdleLock implements MessageQueue.IdleHandler, Runnable {
    private boolean mIsLocked = true;
    private final Object mLock;

    public LooperIdleLock(Object lock, Looper looper) {
        this.mLock = lock;
        looper.getQueue().addIdleHandler(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        Looper.myQueue().addIdleHandler(this);
    }

    @Override // android.os.MessageQueue.IdleHandler
    public boolean queueIdle() {
        synchronized (this.mLock) {
            this.mIsLocked = false;
            this.mLock.notify();
        }
        return false;
    }

    public boolean awaitLocked(long ms) {
        if (this.mIsLocked) {
            try {
                this.mLock.wait(ms);
            } catch (InterruptedException unused) {
            }
        }
        return this.mIsLocked;
    }
}
