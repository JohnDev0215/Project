package Z8;

import X8.t;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public abstract class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f11015a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long f11016b;
    public static final int c;
    public static final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final long f11017e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final g f11018f;

    static {
        String property;
        int i7 = t.f10579a;
        try {
            property = System.getProperty("kotlinx.coroutines.scheduler.default.name");
        } catch (SecurityException unused) {
            property = null;
        }
        if (property == null) {
            property = "DefaultDispatcher";
        }
        f11015a = property;
        f11016b = X8.a.k("kotlinx.coroutines.scheduler.resolution.ns", 100000L, 1L, Long.MAX_VALUE);
        int i10 = t.f10579a;
        if (i10 < 2) {
            i10 = 2;
        }
        c = X8.a.l("kotlinx.coroutines.scheduler.core.pool.size", i10, 1, 0, 8);
        d = X8.a.l("kotlinx.coroutines.scheduler.max.pool.size", 2097150, 0, 2097150, 4);
        f11017e = TimeUnit.SECONDS.toNanos(X8.a.k("kotlinx.coroutines.scheduler.keep.alive.sec", 60L, 1L, Long.MAX_VALUE));
        f11018f = g.f11011a;
    }
}
