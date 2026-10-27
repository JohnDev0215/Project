package D;

import E0.InterfaceC0174y;

/* JADX INFO: loaded from: classes.dex */
public final class U extends g0.n implements InterfaceC0174y {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public float f1051A;
    public float B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public boolean f1052C;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public float f1053y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public float f1054z;

    /* JADX WARN: Removed duplicated region for block: B:23:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long H0(C0.InterfaceC0108o r8) {
        /*
            r7 = this;
            float r0 = r7.f1051A
            r1 = 2143289344(0x7fc00000, float:NaN)
            boolean r0 = a1.e.a(r0, r1)
            r2 = 2147483647(0x7fffffff, float:NaN)
            r3 = 0
            if (r0 != 0) goto L18
            float r0 = r7.f1051A
            int r0 = r8.O(r0)
            if (r0 >= 0) goto L19
            r0 = r3
            goto L19
        L18:
            r0 = r2
        L19:
            float r4 = r7.B
            boolean r4 = a1.e.a(r4, r1)
            if (r4 != 0) goto L2b
            float r4 = r7.B
            int r4 = r8.O(r4)
            if (r4 >= 0) goto L2c
            r4 = r3
            goto L2c
        L2b:
            r4 = r2
        L2c:
            float r5 = r7.f1053y
            boolean r5 = a1.e.a(r5, r1)
            if (r5 != 0) goto L43
            float r5 = r7.f1053y
            int r5 = r8.O(r5)
            if (r5 <= r0) goto L3d
            r5 = r0
        L3d:
            if (r5 >= 0) goto L40
            r5 = r3
        L40:
            if (r5 == r2) goto L43
            goto L44
        L43:
            r5 = r3
        L44:
            float r6 = r7.f1054z
            boolean r1 = a1.e.a(r6, r1)
            if (r1 != 0) goto L5b
            float r7 = r7.f1054z
            int r7 = r8.O(r7)
            if (r7 <= r4) goto L55
            r7 = r4
        L55:
            if (r7 >= 0) goto L58
            r7 = r3
        L58:
            if (r7 == r2) goto L5b
            r3 = r7
        L5b:
            long r7 = io.sentry.config.a.e(r5, r0, r3, r4)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: D.U.H0(C0.o):long");
    }

    @Override // E0.InterfaceC0174y
    public final int b(E0.Q q4, C0.J j3, int i7) {
        long jH0 = H0(q4);
        return a1.a.e(jH0) ? a1.a.g(jH0) : io.sentry.config.a.R(jH0, j3.c(i7));
    }

    @Override // E0.InterfaceC0174y
    public final int c(E0.Q q4, C0.J j3, int i7) {
        long jH0 = H0(q4);
        return a1.a.f(jH0) ? a1.a.h(jH0) : io.sentry.config.a.S(jH0, j3.P(i7));
    }

    @Override // E0.InterfaceC0174y
    public final int d(E0.Q q4, C0.J j3, int i7) {
        long jH0 = H0(q4);
        return a1.a.f(jH0) ? a1.a.h(jH0) : io.sentry.config.a.S(jH0, j3.V(i7));
    }

    @Override // E0.InterfaceC0174y
    public final int f(E0.Q q4, C0.J j3, int i7) {
        long jH0 = H0(q4);
        return a1.a.e(jH0) ? a1.a.g(jH0) : io.sentry.config.a.R(jH0, j3.a0(i7));
    }

    @Override // E0.InterfaceC0174y
    public final C0.L g(C0.M m10, C0.J j3, long j10) {
        int iJ;
        int iH;
        int i7;
        int iG;
        long jE;
        long jH0 = H0(m10);
        if (this.f1052C) {
            jE = io.sentry.config.a.Q(j10, jH0);
        } else {
            if (a1.e.a(this.f1053y, Float.NaN)) {
                iJ = a1.a.j(j10);
                int iH2 = a1.a.h(jH0);
                if (iJ > iH2) {
                    iJ = iH2;
                }
            } else {
                iJ = a1.a.j(jH0);
            }
            if (a1.e.a(this.f1051A, Float.NaN)) {
                iH = a1.a.h(j10);
                int iJ2 = a1.a.j(jH0);
                if (iH < iJ2) {
                    iH = iJ2;
                }
            } else {
                iH = a1.a.h(jH0);
            }
            if (a1.e.a(this.f1054z, Float.NaN)) {
                i7 = a1.a.i(j10);
                int iG2 = a1.a.g(jH0);
                if (i7 > iG2) {
                    i7 = iG2;
                }
            } else {
                i7 = a1.a.i(jH0);
            }
            if (a1.e.a(this.B, Float.NaN)) {
                iG = a1.a.g(j10);
                int i10 = a1.a.i(jH0);
                if (iG < i10) {
                    iG = i10;
                }
            } else {
                iG = a1.a.g(jH0);
            }
            jE = io.sentry.config.a.e(iJ, iH, i7, iG);
        }
        C0.V vB = j3.b(jE);
        return m10.m0(vB.l, vB.f871m, n7.v.l, new C0.X(vB, 4));
    }
}
