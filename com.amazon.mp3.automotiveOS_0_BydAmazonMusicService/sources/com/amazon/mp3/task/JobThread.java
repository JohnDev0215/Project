package com.amazon.mp3.task;

import com.amazon.mp3.task.AbstractMetadata;
import com.amazon.mp3.util.Log;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes4.dex */
public abstract class JobThread<T extends AbstractMetadata> extends Thread {
    private static AtomicInteger sThreadCount = new AtomicInteger(0);
    private JobStack<T> mJobStack;
    private int mThreadNumber;
    private boolean mPaused = false;
    private Object mLockObject = new Object();

    public abstract void execute(JobContext<T> jobContext);

    public JobThread(JobStack<T> jobStack) {
        this.mJobStack = jobStack;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        this.mThreadNumber = sThreadCount.incrementAndGet();
        setName(getClass().getSimpleName() + "-Thread" + this.mThreadNumber);
        Log.debug(getName(), "Thread started: %s", getName());
        while (true) {
            try {
                synchronized (this.mLockObject) {
                    while (this.mPaused) {
                        try {
                            this.mLockObject.wait();
                        } catch (InterruptedException unused) {
                        }
                    }
                }
                try {
                    execute(this.mJobStack.take());
                } finally {
                    try {
                    } finally {
                    }
                }
            } catch (InterruptedException unused2) {
                Log.warning(getName(), "Thread interrupted -- quiting");
                return;
            }
        }
    }

    public void pause() {
        this.mPaused = true;
    }

    public void unpause() {
        this.mPaused = false;
        synchronized (this.mLockObject) {
            this.mLockObject.notifyAll();
        }
    }
}
