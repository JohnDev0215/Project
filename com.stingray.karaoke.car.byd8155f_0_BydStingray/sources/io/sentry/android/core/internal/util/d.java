package io.sentry.android.core.internal.util;

import android.os.SystemClock;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import p0.C2071b;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f16213a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f16214b;
    public Object c;
    public Object d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f16215e;

    public d(long j3, int i7) {
        b bVar = b.f16209a;
        this.d = new AtomicInteger(0);
        this.f16215e = new AtomicLong(0L);
        this.c = bVar;
        this.f16213a = j3;
        this.f16214b = i7 <= 0 ? 1 : i7;
    }

    public boolean a() {
        ((b) this.c).getClass();
        long jUptimeMillis = SystemClock.uptimeMillis();
        AtomicLong atomicLong = (AtomicLong) this.f16215e;
        long j3 = atomicLong.get();
        AtomicInteger atomicInteger = (AtomicInteger) this.d;
        if (j3 == 0 || atomicLong.get() + this.f16213a <= jUptimeMillis) {
            atomicInteger.set(0);
            atomicLong.set(jUptimeMillis);
            return false;
        }
        if (atomicInteger.incrementAndGet() < this.f16214b) {
            return false;
        }
        atomicInteger.set(0);
        return true;
    }

    public d() {
        this.f16213a = 0L;
        this.f16214b = 0;
        this.f16215e = new C2071b();
    }
}
