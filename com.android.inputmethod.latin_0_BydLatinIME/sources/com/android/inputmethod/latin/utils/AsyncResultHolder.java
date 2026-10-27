package com.android.inputmethod.latin.utils;

import android.util.Log;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class AsyncResultHolder<E> {
    private E mResult;
    private final String mTag;
    private final Object mLock = new Object();
    private final CountDownLatch mLatch = new CountDownLatch(1);

    public AsyncResultHolder(final String tag) {
        this.mTag = tag;
    }

    public void set(final E result) {
        synchronized (this.mLock) {
            if (this.mLatch.getCount() > 0) {
                this.mResult = result;
                this.mLatch.countDown();
            }
        }
    }

    public E get(final E defaultValue, final long timeOut) {
        try {
            return this.mLatch.await(timeOut, TimeUnit.MILLISECONDS) ? this.mResult : defaultValue;
        } catch (InterruptedException unused) {
            Log.w(this.mTag, "get() : Interrupted after " + timeOut + " ms");
            return defaultValue;
        }
    }
}
