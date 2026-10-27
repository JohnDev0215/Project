package io.sentry.android.core;

import androidx.lifecycle.DefaultLifecycleObserver;
import io.sentry.C1457d;
import io.sentry.EnumC1495p1;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public final class L implements DefaultLifecycleObserver {
    public final AtomicLong l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AtomicBoolean f16063m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f16064n;
    public H5.f o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Timer f16065p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Object f16066q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final io.sentry.E f16067r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final boolean f16068s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final boolean f16069t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final io.sentry.transport.d f16070u;

    public L(long j3, boolean z6, boolean z10) {
        io.sentry.E e10 = io.sentry.E.f15834a;
        io.sentry.transport.d dVar = io.sentry.transport.d.f16897a;
        this.l = new AtomicLong(0L);
        this.f16063m = new AtomicBoolean(false);
        this.f16065p = new Timer(true);
        this.f16066q = new Object();
        this.f16064n = j3;
        this.f16068s = z6;
        this.f16069t = z10;
        this.f16067r = e10;
        this.f16070u = dVar;
    }

    public final void a(String str) {
        if (this.f16069t) {
            C1457d c1457d = new C1457d();
            c1457d.o = "navigation";
            c1457d.c(str, "state");
            c1457d.f16515q = "app.lifecycle";
            c1457d.f16517s = EnumC1495p1.INFO;
            this.f16067r.e(c1457d);
        }
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onStart(androidx.lifecycle.A a10) {
        synchronized (this.f16066q) {
            try {
                H5.f fVar = this.o;
                if (fVar != null) {
                    fVar.cancel();
                    this.o = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.f16070u.getClass();
        long jCurrentTimeMillis = System.currentTimeMillis();
        G2.a aVar = new G2.a(this, 10);
        io.sentry.E e10 = this.f16067r;
        e10.l(aVar);
        AtomicLong atomicLong = this.l;
        long j3 = atomicLong.get();
        AtomicBoolean atomicBoolean = this.f16063m;
        if (j3 == 0 || j3 + this.f16064n <= jCurrentTimeMillis) {
            if (this.f16068s) {
                e10.p();
            }
            e10.q().getReplayController().start();
        } else if (!atomicBoolean.get()) {
            e10.q().getReplayController().j();
        }
        atomicBoolean.set(false);
        atomicLong.set(jCurrentTimeMillis);
        a("foreground");
        A a11 = A.f16013b;
        synchronized (a11) {
            a11.f16014a = Boolean.FALSE;
        }
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onStop(androidx.lifecycle.A a10) {
        this.f16070u.getClass();
        this.l.set(System.currentTimeMillis());
        this.f16067r.q().getReplayController().b();
        synchronized (this.f16066q) {
            try {
                synchronized (this.f16066q) {
                    try {
                        H5.f fVar = this.o;
                        if (fVar != null) {
                            fVar.cancel();
                            this.o = null;
                        }
                    } finally {
                    }
                }
                if (this.f16065p != null) {
                    H5.f fVar2 = new H5.f(this, 3);
                    this.o = fVar2;
                    this.f16065p.schedule(fVar2, this.f16064n);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        A a11 = A.f16013b;
        synchronized (a11) {
            a11.f16014a = Boolean.TRUE;
        }
        a("background");
    }
}
