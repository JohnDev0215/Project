package kotlinx.coroutines.scheduling;

import java.util.concurrent.TimeUnit;
import kotlinx.coroutines.internal.d0;
import kotlinx.coroutines.internal.f0;

/* JADX INFO: compiled from: Tasks.kt */
/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f7781a = f0.e("kotlinx.coroutines.scheduler.resolution.ns", 100000, 0, 0, 12, null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f7782b = f0.d("kotlinx.coroutines.scheduler.blocking.parallelism", 16, 0, 0, 12, null);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f7783c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f7784d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final long f7785e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static m f7786f;

    static {
        int iD = f0.d("kotlinx.coroutines.scheduler.core.pool.size", v5.h.b(d0.a(), 2), 1, 0, 8, null);
        f7783c = iD;
        f7784d = f0.d("kotlinx.coroutines.scheduler.max.pool.size", v5.h.f(d0.a() * 128, iD, 2097150), 0, 2097150, 4, null);
        f7785e = TimeUnit.SECONDS.toNanos(f0.e("kotlinx.coroutines.scheduler.keep.alive.sec", 60L, 0L, 0L, 12, null));
        f7786f = g.f7775a;
    }
}
