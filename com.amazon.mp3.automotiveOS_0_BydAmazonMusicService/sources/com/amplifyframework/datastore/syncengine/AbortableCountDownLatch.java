package com.amplifyframework.datastore.syncengine;

import java.lang.Throwable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
final class AbortableCountDownLatch<E extends Throwable> extends CountDownLatch {
    private E error;

    AbortableCountDownLatch(int i) {
        super(i);
    }

    public void abort(E e) {
        if (getCount() == 0) {
            return;
        }
        this.error = e;
        while (getCount() > 0) {
            countDown();
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: E extends java.lang.Throwable */
    public boolean abortableAwait(long j, TimeUnit timeUnit) throws Throwable {
        boolean zAwait = super.await(j, timeUnit);
        E e = this.error;
        if (e == null) {
            return zAwait;
        }
        throw e;
    }
}
