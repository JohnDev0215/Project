package spi;

import android.os.SystemClock;

/* JADX INFO: loaded from: classes.dex */
public final class ch {
    public static final double a = 1.0d / Math.pow(10.0d, 6.0d);
    public static final /* synthetic */ int b = 0;

    private ch() {
    }

    public static double a(long j) {
        return (SystemClock.elapsedRealtimeNanos() - j) * a;
    }
}
