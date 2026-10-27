package com.android.internal.telephony;

/* JADX INFO: loaded from: classes.dex */
public final class SomeArgs {
    private static SomeArgs sPool;
    private static Object sPoolLock = new Object();
    private static int sPoolSize;
    public Object arg1;
    public Object arg2;
    public Object arg3;
    public Object arg4;
    private boolean mInPool;
    private SomeArgs mNext;
    int mWaitState = 0;

    private SomeArgs() {
    }

    public static SomeArgs obtain() {
        synchronized (sPoolLock) {
            if (sPoolSize > 0) {
                SomeArgs someArgs = sPool;
                sPool = sPool.mNext;
                someArgs.mNext = null;
                someArgs.mInPool = false;
                sPoolSize--;
                return someArgs;
            }
            return new SomeArgs();
        }
    }

    public void recycle() {
        if (this.mInPool) {
            throw new IllegalStateException("Already recycled.");
        }
        if (this.mWaitState != 0) {
            return;
        }
        synchronized (sPoolLock) {
            clear();
            if (sPoolSize < 10) {
                this.mNext = sPool;
                this.mInPool = true;
                sPool = this;
                sPoolSize++;
            }
        }
    }

    private void clear() {
        this.arg1 = null;
        this.arg2 = null;
        this.arg3 = null;
        this.arg4 = null;
    }
}
