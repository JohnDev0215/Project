package p;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public abstract class fj2 {
    public static final String a;
    public static final long b;
    public static final int c;
    public static final int d;
    public static final long e;
    public static final bm1 f;
    public static final w1 g;
    public static final w1 h;

    static {
        String property;
        int i = pi2.a;
        try {
            property = System.getProperty("kotlinx.coroutines.scheduler.default.name");
        } catch (SecurityException unused) {
            property = null;
        }
        if (property == null) {
            property = "DefaultDispatcher";
        }
        a = property;
        b = v03.N("kotlinx.coroutines.scheduler.resolution.ns", 100000L, 1L, Long.MAX_VALUE);
        int i2 = pi2.a;
        if (i2 < 2) {
            i2 = 2;
        }
        c = v03.O("kotlinx.coroutines.scheduler.core.pool.size", i2, 1, 0, 8);
        d = v03.O("kotlinx.coroutines.scheduler.max.pool.size", 2097150, 0, 2097150, 4);
        e = TimeUnit.SECONDS.toNanos(v03.N("kotlinx.coroutines.scheduler.keep.alive.sec", 60L, 1L, Long.MAX_VALUE));
        f = bm1.q;
        g = new w1(0);
        h = new w1(1);
    }
}
