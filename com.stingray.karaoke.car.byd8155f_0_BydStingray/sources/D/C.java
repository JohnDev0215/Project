package D;

import E0.InterfaceC0174y;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class C extends g0.n implements InterfaceC0174y {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f1022y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public float f1023z;

    @Override // E0.InterfaceC0174y
    public final C0.L g(C0.M m10, C0.J j3, long j10) {
        int iJ;
        int iH;
        int i7;
        int iG;
        if (!a1.a.d(j10) || this.f1022y == 1) {
            iJ = a1.a.j(j10);
            iH = a1.a.h(j10);
        } else {
            iJ = AbstractC2673a.v(Math.round(a1.a.h(j10) * this.f1023z), a1.a.j(j10), a1.a.h(j10));
            iH = iJ;
        }
        if (!a1.a.c(j10) || this.f1022y == 2) {
            i7 = a1.a.i(j10);
            iG = a1.a.g(j10);
        } else {
            i7 = AbstractC2673a.v(Math.round(a1.a.g(j10) * this.f1023z), a1.a.i(j10), a1.a.g(j10));
            iG = i7;
        }
        C0.V vB = j3.b(io.sentry.config.a.e(iJ, iH, i7, iG));
        return m10.m0(vB.l, vB.f871m, n7.v.l, new C0.X(vB, 2));
    }
}
