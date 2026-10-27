package io.sentry.android.core;

import android.os.SystemClock;

/* JADX INFO: renamed from: io.sentry.android.core.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1437m implements io.sentry.android.core.internal.util.k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f16243a = 0.0f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C1439o f16244b;

    public C1437m(C1439o c1439o) {
        this.f16244b = c1439o;
    }

    @Override // io.sentry.android.core.internal.util.k
    public final void c(long j3, long j10, long j11, long j12, boolean z6, boolean z10, float f10) {
        long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos() + (j10 - System.nanoTime());
        C1439o c1439o = this.f16244b;
        long j13 = jElapsedRealtimeNanos - c1439o.f16248a;
        if (j13 < 0) {
            return;
        }
        if (z10) {
            c1439o.f16255j.addLast(new io.sentry.profilemeasurements.b(Long.valueOf(j13), Long.valueOf(j11)));
        } else if (z6) {
            c1439o.f16254i.addLast(new io.sentry.profilemeasurements.b(Long.valueOf(j13), Long.valueOf(j11)));
        }
        if (f10 != this.f16243a) {
            this.f16243a = f10;
            c1439o.f16253h.addLast(new io.sentry.profilemeasurements.b(Long.valueOf(j13), Float.valueOf(f10)));
        }
    }
}
