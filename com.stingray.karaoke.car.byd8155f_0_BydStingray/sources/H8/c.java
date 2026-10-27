package H8;

import A7.m;
import B2.q;
import Q7.I;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0659l;
import Q7.InterfaceC0671y;
import java.util.Collection;
import java.util.List;
import n7.u;
import o8.C2058c;
import o8.C2061f;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class c implements InterfaceC0671y {
    public static final c l = new c();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final C2061f f2855m = C2061f.g("<Error module>");

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final u f2856n = u.l;
    public static final N7.e o;

    static {
        N7.e eVar = N7.e.f4791f;
        o = N7.e.f4791f;
    }

    @Override // Q7.InterfaceC0671y
    public final Object P(q qVar) {
        m.f(qVar, "capability");
        return null;
    }

    @Override // Q7.InterfaceC0657j
    public final Object S(InterfaceC0659l interfaceC0659l, Object obj) {
        return null;
    }

    @Override // Q7.InterfaceC0671y
    public final List Z() {
        return f2856n;
    }

    @Override // Q7.InterfaceC0657j, Q7.InterfaceC0654g
    /* JADX INFO: renamed from: a */
    public final InterfaceC0657j b1() {
        return this;
    }

    @Override // Q7.InterfaceC0657j
    public final C2061f getName() {
        return f2855m;
    }

    @Override // R7.a
    public final R7.h k() {
        return R7.g.f7535a;
    }

    @Override // Q7.InterfaceC0671y
    public final N7.i l() {
        return o;
    }

    @Override // Q7.InterfaceC0671y
    public final I n0(C2058c c2058c) {
        m.f(c2058c, "fqName");
        throw new IllegalStateException("Should not be called!");
    }

    @Override // Q7.InterfaceC0657j
    public final InterfaceC0657j o() {
        return null;
    }

    @Override // Q7.InterfaceC0671y
    public final Collection r(C2058c c2058c, InterfaceC2782k interfaceC2782k) {
        m.f(c2058c, "fqName");
        m.f(interfaceC2782k, "nameFilter");
        return u.l;
    }

    @Override // Q7.InterfaceC0671y
    public final boolean u0(InterfaceC0671y interfaceC0671y) {
        m.f(interfaceC0671y, "targetModule");
        return false;
    }
}
