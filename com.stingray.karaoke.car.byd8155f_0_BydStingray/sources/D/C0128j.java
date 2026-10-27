package D;

import E0.InterfaceC0174y;

/* JADX INFO: renamed from: D.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0128j extends g0.n implements InterfaceC0174y {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public float f1080y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f1081z;

    public final long H0(long j3, boolean z6) {
        int iRound;
        int iG = a1.a.g(j3);
        if (iG == Integer.MAX_VALUE || (iRound = Math.round(iG * this.f1080y)) <= 0) {
            return 0L;
        }
        long jC = H7.H.c(iRound, iG);
        if (!z6 || io.sentry.config.a.e0(j3, jC)) {
            return jC;
        }
        return 0L;
    }

    public final long I0(long j3, boolean z6) {
        int iRound;
        int iH = a1.a.h(j3);
        if (iH == Integer.MAX_VALUE || (iRound = Math.round(iH / this.f1080y)) <= 0) {
            return 0L;
        }
        long jC = H7.H.c(iH, iRound);
        if (!z6 || io.sentry.config.a.e0(j3, jC)) {
            return jC;
        }
        return 0L;
    }

    public final long J0(long j3, boolean z6) {
        int i7 = a1.a.i(j3);
        int iRound = Math.round(i7 * this.f1080y);
        if (iRound <= 0) {
            return 0L;
        }
        long jC = H7.H.c(iRound, i7);
        if (!z6 || io.sentry.config.a.e0(j3, jC)) {
            return jC;
        }
        return 0L;
    }

    public final long K0(long j3, boolean z6) {
        int iJ = a1.a.j(j3);
        int iRound = Math.round(iJ / this.f1080y);
        if (iRound <= 0) {
            return 0L;
        }
        long jC = H7.H.c(iJ, iRound);
        if (!z6 || io.sentry.config.a.e0(j3, jC)) {
            return jC;
        }
        return 0L;
    }

    @Override // E0.InterfaceC0174y
    public final int b(E0.Q q4, C0.J j3, int i7) {
        return i7 != Integer.MAX_VALUE ? Math.round(i7 / this.f1080y) : j3.c(i7);
    }

    @Override // E0.InterfaceC0174y
    public final int c(E0.Q q4, C0.J j3, int i7) {
        return i7 != Integer.MAX_VALUE ? Math.round(i7 * this.f1080y) : j3.P(i7);
    }

    @Override // E0.InterfaceC0174y
    public final int d(E0.Q q4, C0.J j3, int i7) {
        return i7 != Integer.MAX_VALUE ? Math.round(i7 * this.f1080y) : j3.V(i7);
    }

    @Override // E0.InterfaceC0174y
    public final int f(E0.Q q4, C0.J j3, int i7) {
        return i7 != Integer.MAX_VALUE ? Math.round(i7 / this.f1080y) : j3.a0(i7);
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x00bf  */
    @Override // E0.InterfaceC0174y
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final C0.L g(C0.M r8, C0.J r9, long r10) {
        /*
            Method dump skipped, instruction units count: 272
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: D.C0128j.g(C0.M, C0.J, long):C0.L");
    }
}
