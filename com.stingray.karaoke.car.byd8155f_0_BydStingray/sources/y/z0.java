package y;

import A.EnumC0017h0;
import E0.InterfaceC0174y;
import T.C0732b0;
import e0.AbstractC1167g;
import e0.AbstractC1179s;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class z0 extends g0.n implements InterfaceC0174y {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f24036A;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public y0 f24037y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f24038z;

    @Override // E0.InterfaceC0174y
    public final int b(E0.Q q4, C0.J j3, int i7) {
        return this.f24036A ? j3.c(i7) : j3.c(Integer.MAX_VALUE);
    }

    @Override // E0.InterfaceC0174y
    public final int c(E0.Q q4, C0.J j3, int i7) {
        return this.f24036A ? j3.P(Integer.MAX_VALUE) : j3.P(i7);
    }

    @Override // E0.InterfaceC0174y
    public final int d(E0.Q q4, C0.J j3, int i7) {
        return this.f24036A ? j3.V(Integer.MAX_VALUE) : j3.V(i7);
    }

    @Override // E0.InterfaceC0174y
    public final int f(E0.Q q4, C0.J j3, int i7) {
        return this.f24036A ? j3.a0(i7) : j3.a0(Integer.MAX_VALUE);
    }

    @Override // E0.InterfaceC0174y
    public final C0.L g(C0.M m10, C0.J j3, long j10) {
        h6.m.h(j10, this.f24036A ? EnumC0017h0.l : EnumC0017h0.f181m);
        C0.V vB = j3.b(a1.a.a(j10, 0, this.f24036A ? a1.a.h(j10) : Integer.MAX_VALUE, 0, this.f24036A ? Integer.MAX_VALUE : a1.a.g(j10), 5));
        int i7 = vB.l;
        int iH = a1.a.h(j10);
        if (i7 > iH) {
            i7 = iH;
        }
        int i10 = vB.f871m;
        int iG = a1.a.g(j10);
        if (i10 > iG) {
            i10 = iG;
        }
        int i11 = vB.f871m - i10;
        int i12 = vB.l - i7;
        if (!this.f24036A) {
            i11 = i12;
        }
        y0 y0Var = this.f24037y;
        C0732b0 c0732b0 = y0Var.d;
        C0732b0 c0732b02 = y0Var.f24028a;
        c0732b0.j(i11);
        AbstractC1167g abstractC1167gC = AbstractC1179s.c();
        InterfaceC2782k interfaceC2782kF = abstractC1167gC != null ? abstractC1167gC.f() : null;
        AbstractC1167g abstractC1167gD = AbstractC1179s.d(abstractC1167gC);
        try {
            if (c0732b02.i() > i11) {
                c0732b02.j(i11);
            }
            AbstractC1179s.f(abstractC1167gC, abstractC1167gD, interfaceC2782kF);
            this.f24037y.f24029b.j(this.f24036A ? i10 : i7);
            return m10.m0(i7, i10, n7.v.l, new K.t0(this, i11, vB, 2));
        } catch (Throwable th) {
            AbstractC1179s.f(abstractC1167gC, abstractC1167gD, interfaceC2782kF);
            throw th;
        }
    }
}
