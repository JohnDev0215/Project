package io.reactivex.rxjava3.parallel;

import spi.o1;

/* JADX INFO: loaded from: classes2.dex */
public enum ParallelFailureHandling implements o1<Long, Throwable, ParallelFailureHandling> {
    STOP,
    ERROR,
    SKIP,
    RETRY;

    public ParallelFailureHandling apply(Long l, Throwable th) {
        return this;
    }
}
