package io.sentry.util;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public final class g implements Serializable {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final AtomicLong f16923m = new AtomicLong(8682522807148012L);
    public final AtomicLong l;

    public g() {
        AtomicLong atomicLong;
        long j3;
        long j10;
        do {
            atomicLong = f16923m;
            j3 = atomicLong.get();
            j10 = 1181783497276652981L * j3;
        } while (!atomicLong.compareAndSet(j3, j10));
        this.l = new AtomicLong(((System.nanoTime() ^ j10) ^ 25214903917L) & 281474976710655L);
    }

    public final int a(int i7) {
        long j3;
        long j10;
        AtomicLong atomicLong = this.l;
        do {
            j3 = atomicLong.get();
            j10 = ((25214903917L * j3) + 11) & 281474976710655L;
        } while (!atomicLong.compareAndSet(j3, j10));
        return (int) (j10 >>> (48 - i7));
    }

    public final double b() {
        return ((((long) a(26)) << 27) + ((long) a(27))) * 1.1102230246251565E-16d;
    }
}
