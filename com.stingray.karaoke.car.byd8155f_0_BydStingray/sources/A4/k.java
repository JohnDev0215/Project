package A4;

import android.os.SystemClock;

/* JADX INFO: loaded from: classes.dex */
public abstract class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final double f422a = 1.0d / Math.pow(10.0d, 6.0d);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f423b = 0;

    public static double a(long j3) {
        return (SystemClock.elapsedRealtimeNanos() - j3) * f422a;
    }
}
