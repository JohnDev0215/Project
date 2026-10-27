package io.sentry.android.core.performance;

import android.os.SystemClock;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class f implements Comparable {
    public String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f16287m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f16288n;
    public long o;

    public final boolean a() {
        return this.o == 0;
    }

    public final boolean b() {
        return this.f16288n != 0;
    }

    public final boolean c() {
        return this.o != 0;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return Long.compare(this.f16287m, ((f) obj).f16287m);
    }

    public final void d(long j3) {
        this.f16288n = j3;
        long jUptimeMillis = SystemClock.uptimeMillis() - this.f16288n;
        this.f16287m = System.currentTimeMillis() - jUptimeMillis;
        System.nanoTime();
        TimeUnit.MILLISECONDS.toNanos(jUptimeMillis);
    }

    public final void e() {
        this.o = SystemClock.uptimeMillis();
    }
}
