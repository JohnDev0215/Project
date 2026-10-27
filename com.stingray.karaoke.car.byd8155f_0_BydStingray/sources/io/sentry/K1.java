package io.sentry;

import io.sentry.protocol.C1498c;

/* JADX INFO: loaded from: classes.dex */
public final class K1 implements InterfaceC1511t {
    public final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f15889m;

    public K1() {
        String property = System.getProperty("java.version");
        String property2 = System.getProperty("java.vendor");
        this.l = property;
        this.f15889m = property2;
    }

    public final void a(X0 x02) {
        io.sentry.protocol.v vVar = (io.sentry.protocol.v) x02.f15978m.f(io.sentry.protocol.v.class, "runtime");
        C1498c c1498c = x02.f15978m;
        if (vVar == null) {
            c1498c.put("runtime", new io.sentry.protocol.v());
        }
        io.sentry.protocol.v vVar2 = (io.sentry.protocol.v) c1498c.f(io.sentry.protocol.v.class, "runtime");
        if (vVar2 != null && vVar2.l == null && vVar2.f16777m == null) {
            vVar2.l = this.f15889m;
            vVar2.f16777m = this.l;
        }
    }

    @Override // io.sentry.InterfaceC1511t
    public final io.sentry.protocol.A g(io.sentry.protocol.A a10, C1523x c1523x) {
        a(a10);
        return a10;
    }

    @Override // io.sentry.InterfaceC1511t
    public final C1480k1 i(C1480k1 c1480k1, C1523x c1523x) {
        a(c1480k1);
        return c1480k1;
    }
}
