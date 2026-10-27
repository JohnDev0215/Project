package D;

import E0.InterfaceC0174y;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class a0 extends g0.n implements InterfaceC0174y {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public A7.o f1065A;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f1066y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f1067z;

    @Override // E0.InterfaceC0174y
    public final C0.L g(C0.M m10, C0.J j3, long j10) {
        C0.V vB = j3.b(io.sentry.config.a.e(this.f1066y != 1 ? 0 : a1.a.j(j10), (this.f1066y == 1 || !this.f1067z) ? a1.a.h(j10) : Integer.MAX_VALUE, this.f1066y == 2 ? a1.a.i(j10) : 0, (this.f1066y == 2 || !this.f1067z) ? a1.a.g(j10) : Integer.MAX_VALUE));
        int iV = AbstractC2673a.v(vB.l, a1.a.j(j10), a1.a.h(j10));
        int iV2 = AbstractC2673a.v(vB.f871m, a1.a.i(j10), a1.a.g(j10));
        return m10.m0(iV, iV2, n7.v.l, new Z(this, iV, vB, iV2, m10));
    }
}
