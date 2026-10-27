package com.amazon.avod.media.framework.retry;

import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ExceptionBasedRetryPolicy<T> implements RetryPolicy<T> {
    private final List<Class<? extends Exception>> mRetriableExceptionClasses;

    @Override // com.amazon.avod.media.framework.retry.RetryPolicy
    public boolean shouldRetryWithResult(T t) {
        return false;
    }

    public ExceptionBasedRetryPolicy(List<Class<? extends Exception>> list) {
        this.mRetriableExceptionClasses = list;
    }

    @Override // com.amazon.avod.media.framework.retry.RetryPolicy
    public boolean shouldRetryOnException(Exception exc) {
        return isRetriableException(exc);
    }

    private boolean isRetriableException(Exception exc) {
        Class<?> cls = exc.getClass();
        Iterator<Class<? extends Exception>> it = this.mRetriableExceptionClasses.iterator();
        while (it.hasNext()) {
            if (it.next().isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }
}
