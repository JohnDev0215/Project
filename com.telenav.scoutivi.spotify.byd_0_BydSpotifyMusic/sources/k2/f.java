package k2;

import android.annotation.TargetApi;
import android.os.SystemClock;

/* JADX INFO: compiled from: LogTime.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final double f7249a = 1.0d / Math.pow(10.0d, 6.0d);

    public static double a(long j7) {
        return (b() - j7) * f7249a;
    }

    @TargetApi(17)
    public static long b() {
        return SystemClock.elapsedRealtimeNanos();
    }
}
