package com.amazon.identity.auth.device;

import android.util.Log;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class bd {
    public static final long f;
    public static final long g;
    public static final long h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final URL f248a;
    public final long b;
    public final long c;
    public final SecureRandom d;
    public int e;

    static {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        f = timeUnit.toMillis(1L);
        g = timeUnit.toMillis(60L);
        h = timeUnit.toMillis(78L);
    }

    public bd(URL url, long j) {
        this(1, url, j);
    }

    public bd a(URL url) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = this.c;
        boolean z = jCurrentTimeMillis < j;
        boolean z2 = j - jCurrentTimeMillis < h;
        if (z && z2) {
            return this;
        }
        int iMin = (int) Math.min(this.b * 2, g);
        Log.i(ga.a("BackoffInfo"), String.format(Locale.ENGLISH, "Last backoff interval is %d ms, updating backoff info...", Long.valueOf(this.b)));
        int i = this.e + 1;
        this.e = i;
        return new bd(i, url, cd.a(iMin, 30, this.d));
    }

    public bd(int i, URL url, long j) {
        this.e = 0;
        this.f248a = url;
        this.d = new SecureRandom();
        long jA = a(j);
        this.b = jA;
        this.c = jA + System.currentTimeMillis();
        this.e = i;
    }

    public final long a(long j) {
        long j2 = f;
        if (j <= j2) {
            Log.i(ga.a("BackoffInfo"), String.format(Locale.ENGLISH, "Backoff interval cannot be less than %d ms, set interval to %d ms", Long.valueOf(j2), Long.valueOf(j2)));
            return cd.a(j, 30, this.d);
        }
        return Math.min(j, h);
    }
}
