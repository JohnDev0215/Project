package io.reactivex.internal.util;

import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes7.dex */
public final class BackpressureHelper {
    public static long addCap(long j, long j2) {
        long j3 = j + j2;
        if (j3 < 0) {
            return Long.MAX_VALUE;
        }
        return j3;
    }

    private BackpressureHelper() {
        throw new IllegalStateException("No instances!");
    }

    public static long add(AtomicLong atomicLong, long j) {
        long j2;
        do {
            j2 = atomicLong.get();
            if (j2 == Long.MAX_VALUE) {
                return Long.MAX_VALUE;
            }
        } while (!atomicLong.compareAndSet(j2, addCap(j2, j)));
        return j2;
    }
}
