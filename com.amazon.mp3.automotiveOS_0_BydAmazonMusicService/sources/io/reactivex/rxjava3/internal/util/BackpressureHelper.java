package io.reactivex.rxjava3.internal.util;

import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes7.dex */
public final class BackpressureHelper {
    public static long addCap(long a2, long b) {
        long j = a2 + b;
        if (j < 0) {
            return Long.MAX_VALUE;
        }
        return j;
    }

    private BackpressureHelper() {
        throw new IllegalStateException("No instances!");
    }

    public static long add(AtomicLong requested, long n) {
        long j;
        do {
            j = requested.get();
            if (j == Long.MAX_VALUE) {
                return Long.MAX_VALUE;
            }
        } while (!requested.compareAndSet(j, addCap(j, n)));
        return j;
    }
}
