package w;

import F8.C0285u;
import x.AbstractC2631e;
import x.C0;
import x.C2640i0;
import x.D0;
import x.E0;
import x.InterfaceC2615D;
import x.L0;
import x3.AbstractC2673a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: w.A, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2565A {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final D0 f23045a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C2640i0 f23046b;
    public static final C2640i0 c;
    public static final C2640i0 d;

    static {
        C2584o c2584o = C2584o.f23123r;
        C2584o c2584o2 = C2584o.f23124s;
        D0 d02 = E0.f23396a;
        f23045a = new D0(c2584o, c2584o2);
        f23046b = AbstractC2631e.p(400.0f, 5, null);
        Object obj = L0.f23437a;
        c = AbstractC2631e.p(400.0f, 1, new a1.h(AbstractC2673a.d(1, 1)));
        d = AbstractC2631e.p(400.0f, 1, new a1.j(H7.H.c(1, 1)));
    }

    public static F a(g0.e eVar, int i7) {
        Object obj = L0.f23437a;
        C2640i0 c2640i0P = AbstractC2631e.p(400.0f, 1, new a1.j(H7.H.c(1, 1)));
        int i10 = i7 & 2;
        g0.e eVar2 = g0.b.f14674z;
        if (i10 != 0) {
            eVar = eVar2;
        }
        return new F(new P(null, null, new t(A7.m.a(eVar, g0.b.f14672x) ? g0.b.o : A7.m.a(eVar, eVar2) ? g0.b.f14665q : g0.b.f14664p, new C0285u(4, C2584o.f23126u), c2640i0P, true), null, false, null, 59));
    }

    public static F b(C0 c02, int i7) {
        InterfaceC2615D interfaceC2615DP = c02;
        if ((i7 & 1) != 0) {
            interfaceC2615DP = AbstractC2631e.p(400.0f, 5, null);
        }
        return new F(new P(new H(0.0f, interfaceC2615DP), null, null, null, false, null, 62));
    }

    public static G c(C0 c02, int i7) {
        InterfaceC2615D interfaceC2615DP = c02;
        if ((i7 & 1) != 0) {
            interfaceC2615DP = AbstractC2631e.p(400.0f, 5, null);
        }
        return new G(new P(new H(0.0f, interfaceC2615DP), null, null, null, false, null, 62));
    }

    public static G d(g0.e eVar, int i7) {
        Object obj = L0.f23437a;
        C2640i0 c2640i0P = AbstractC2631e.p(400.0f, 1, new a1.j(H7.H.c(1, 1)));
        int i10 = i7 & 2;
        g0.e eVar2 = g0.b.f14674z;
        if (i10 != 0) {
            eVar = eVar2;
        }
        return new G(new P(null, null, new t(A7.m.a(eVar, g0.b.f14672x) ? g0.b.o : A7.m.a(eVar, eVar2) ? g0.b.f14665q : g0.b.f14664p, new C0285u(5, C2584o.f23127v), c2640i0P, true), null, false, null, 59));
    }

    public static final F e(C0 c02, InterfaceC2782k interfaceC2782k) {
        return new F(new P(null, new N(c02, new C0285u(6, interfaceC2782k)), null, null, false, null, 61));
    }

    public static final G f(C0 c02, InterfaceC2782k interfaceC2782k) {
        return new G(new P(null, new N(c02, new C0285u(7, interfaceC2782k)), null, null, false, null, 61));
    }
}
