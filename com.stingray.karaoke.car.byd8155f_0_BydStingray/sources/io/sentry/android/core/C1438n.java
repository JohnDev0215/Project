package io.sentry.android.core;

import android.os.SystemClock;
import h2.AbstractC1343A;
import java.io.File;
import java.util.HashMap;

/* JADX INFO: renamed from: io.sentry.android.core.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1438n implements n2.H {
    public boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f16245m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f16246n;
    public final Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Object f16247p;

    public C1438n(h2.v vVar) {
        this.o = vVar;
        this.f16247p = e2.P.d;
    }

    public void b(long j3) {
        this.f16245m = j3;
        if (this.l) {
            ((h2.v) this.o).getClass();
            this.f16246n = SystemClock.elapsedRealtime();
        }
    }

    @Override // n2.H
    public long c() {
        long j3 = this.f16245m;
        if (!this.l) {
            return j3;
        }
        ((h2.v) this.o).getClass();
        long jElapsedRealtime = SystemClock.elapsedRealtime() - this.f16246n;
        e2.P p10 = (e2.P) this.f16247p;
        return j3 + (p10.f13995a == 1.0f ? AbstractC1343A.H(jElapsedRealtime) : jElapsedRealtime * ((long) p10.c));
    }

    public void d() {
        if (this.l) {
            return;
        }
        ((h2.v) this.o).getClass();
        this.f16246n = SystemClock.elapsedRealtime();
        this.l = true;
    }

    @Override // n2.H
    public void f(e2.P p10) {
        if (this.l) {
            b(c());
        }
        this.f16247p = p10;
    }

    @Override // n2.H
    public e2.P j() {
        return (e2.P) this.f16247p;
    }

    public C1438n(long j3, long j10, boolean z6, File file, HashMap map) {
        this.f16245m = j3;
        this.o = file;
        this.f16246n = j10;
        this.f16247p = map;
        this.l = z6;
    }
}
