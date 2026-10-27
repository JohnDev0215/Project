package defpackage;

import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: Tasks.kt */
/* JADX INFO: loaded from: classes.dex */
public final class e73 {
    public static final long a = n3.q1("kotlinx.coroutines.scheduler.resolution.ns", 100000, 1, Long.MAX_VALUE);
    public static final int b;
    public static final int c;
    public static final long d;
    public static final bk1 e;
    public static final x63 f;
    public static final x63 g;

    static {
        int i = gy2.a;
        if (i < 2) {
            i = 2;
        }
        b = n3.r1("kotlinx.coroutines.scheduler.core.pool.size", i, 1, 0, 8);
        c = n3.r1("kotlinx.coroutines.scheduler.max.pool.size", 2097150, 0, 2097150, 4);
        d = TimeUnit.SECONDS.toNanos(n3.q1("kotlinx.coroutines.scheduler.keep.alive.sec", 60L, 1L, Long.MAX_VALUE));
        e = bk1.k;
        f = new x63(0);
        g = new x63(1);
    }
}
