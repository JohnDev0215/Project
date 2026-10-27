package io.sentry.android.core;

import android.os.SystemClock;
import android.system.Os;
import android.system.OsConstants;
import h8.AbstractC1386c;
import io.sentry.C1466g;
import io.sentry.E0;
import io.sentry.EnumC1495p1;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: io.sentry.android.core.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1432h implements io.sentry.P {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final io.sentry.K f16173g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final B f16174h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f16169a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f16170b = 0;
    public long c = 1;
    public long d = 1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public double f16171e = 1.0E9d / 1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final File f16172f = new File("/proc/self/stat");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f16175i = false;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Pattern f16176j = Pattern.compile("[\n\t\r ]");

    public C1432h(io.sentry.K k10, B b10) {
        h6.e.q(k10, "Logger is required.");
        this.f16173g = k10;
        this.f16174h = b10;
    }

    @Override // io.sentry.P
    public final void a(E0 e02) {
        this.f16174h.getClass();
        if (this.f16175i) {
            long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
            long j3 = jElapsedRealtimeNanos - this.f16169a;
            this.f16169a = jElapsedRealtimeNanos;
            long jC = c();
            long j10 = jC - this.f16170b;
            this.f16170b = jC;
            e02.f15836b = new C1466g(System.currentTimeMillis(), ((j10 / j3) / this.d) * 100.0d);
        }
    }

    @Override // io.sentry.P
    public final void b() {
        this.f16174h.getClass();
        this.f16175i = true;
        this.c = Os.sysconf(OsConstants._SC_CLK_TCK);
        this.d = Os.sysconf(OsConstants._SC_NPROCESSORS_CONF);
        this.f16171e = 1.0E9d / this.c;
        this.f16170b = c();
    }

    public final long c() {
        String strN;
        io.sentry.K k10 = this.f16173g;
        try {
            strN = AbstractC1386c.n(this.f16172f);
        } catch (IOException e10) {
            this.f16175i = false;
            k10.s(EnumC1495p1.WARNING, "Unable to read /proc/self/stat file. Disabling cpu collection.", e10);
            strN = null;
        }
        if (strN != null) {
            String[] strArrSplit = this.f16176j.split(strN.trim());
            try {
                long j3 = Long.parseLong(strArrSplit[13]);
                long j10 = Long.parseLong(strArrSplit[14]);
                return (long) ((j3 + j10 + Long.parseLong(strArrSplit[15]) + Long.parseLong(strArrSplit[16])) * this.f16171e);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e11) {
                k10.s(EnumC1495p1.ERROR, "Error parsing /proc/self/stat file.", e11);
            }
        }
        return 0L;
    }
}
