package D;

import E0.InterfaceC0174y;

/* JADX INFO: loaded from: classes.dex */
public final class V extends g0.n implements InterfaceC0174y {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public float f1055y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public float f1056z;

    @Override // E0.InterfaceC0174y
    public final int b(E0.Q q4, C0.J j3, int i7) {
        int iC = j3.c(i7);
        int iO = !a1.e.a(this.f1056z, Float.NaN) ? q4.O(this.f1056z) : 0;
        return iC < iO ? iO : iC;
    }

    @Override // E0.InterfaceC0174y
    public final int c(E0.Q q4, C0.J j3, int i7) {
        int iP = j3.P(i7);
        int iO = !a1.e.a(this.f1055y, Float.NaN) ? q4.O(this.f1055y) : 0;
        return iP < iO ? iO : iP;
    }

    @Override // E0.InterfaceC0174y
    public final int d(E0.Q q4, C0.J j3, int i7) {
        int iV = j3.V(i7);
        int iO = !a1.e.a(this.f1055y, Float.NaN) ? q4.O(this.f1055y) : 0;
        return iV < iO ? iO : iV;
    }

    @Override // E0.InterfaceC0174y
    public final int f(E0.Q q4, C0.J j3, int i7) {
        int iA0 = j3.a0(i7);
        int iO = !a1.e.a(this.f1056z, Float.NaN) ? q4.O(this.f1056z) : 0;
        return iA0 < iO ? iO : iA0;
    }

    @Override // E0.InterfaceC0174y
    public final C0.L g(C0.M m10, C0.J j3, long j10) {
        int iJ;
        int i7 = 0;
        if (a1.e.a(this.f1055y, Float.NaN) || a1.a.j(j10) != 0) {
            iJ = a1.a.j(j10);
        } else {
            iJ = m10.O(this.f1055y);
            int iH = a1.a.h(j10);
            if (iJ > iH) {
                iJ = iH;
            }
            if (iJ < 0) {
                iJ = 0;
            }
        }
        int iH2 = a1.a.h(j10);
        if (a1.e.a(this.f1056z, Float.NaN) || a1.a.i(j10) != 0) {
            i7 = a1.a.i(j10);
        } else {
            int iO = m10.O(this.f1056z);
            int iG = a1.a.g(j10);
            if (iO > iG) {
                iO = iG;
            }
            if (iO >= 0) {
                i7 = iO;
            }
        }
        C0.V vB = j3.b(io.sentry.config.a.e(iJ, iH2, i7, a1.a.g(j10)));
        return m10.m0(vB.l, vB.f871m, n7.v.l, new C0.X(vB, 5));
    }
}
