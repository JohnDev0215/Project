package io.sentry.cache;

import A2.m;
import T1.k;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import io.sentry.N0;
import io.sentry.P0;
import io.sentry.T1;
import io.sentry.Y1;
import io.sentry.protocol.C1498c;
import io.sentry.protocol.t;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class f extends P0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F1 f16495a;

    public f(F1 f12) {
        this.f16495a = f12;
    }

    public static Object i(F1 f12, String str, Class cls) {
        return a.b(f12, ".scope-cache", str, cls, null);
    }

    @Override // io.sentry.P0, io.sentry.Q
    public final void a(String str) {
        j(new m(this, 17, str));
    }

    @Override // io.sentry.P0, io.sentry.Q
    public final void c(t tVar) {
        j(new m(this, 16, tVar));
    }

    @Override // io.sentry.P0, io.sentry.Q
    public final void d(ConcurrentHashMap concurrentHashMap) {
        j(new m(this, 13, concurrentHashMap));
    }

    @Override // io.sentry.P0, io.sentry.Q
    public final void f(T1 t12, N0 n02) {
        j(new k(this, t12, n02, 8));
    }

    @Override // io.sentry.P0, io.sentry.Q
    public final void g(C1498c c1498c) {
        j(new m(this, 14, c1498c));
    }

    @Override // io.sentry.P0, io.sentry.Q
    public final void h(Y1 y12) {
        j(new m(this, 15, y12));
    }

    public final void j(Runnable runnable) {
        F1 f12 = this.f16495a;
        if (Thread.currentThread().getName().contains("SentryExecutor")) {
            runnable.run();
            return;
        }
        try {
            f12.getExecutorService().submit(new m(this, 18, runnable));
        } catch (Throwable th) {
            f12.getLogger().s(EnumC1495p1.ERROR, "Serialization task could not be scheduled", th);
        }
    }

    public final void k(Object obj, String str) {
        a.c(this.f16495a, obj, ".scope-cache", str);
    }
}
