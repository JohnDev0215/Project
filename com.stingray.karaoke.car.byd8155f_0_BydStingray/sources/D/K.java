package D;

import A.C0022k;
import E0.InterfaceC0174y;

/* JADX INFO: loaded from: classes.dex */
public final class K extends g0.n implements InterfaceC0174y {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public float f1034A;
    public float B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public boolean f1035C;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public float f1036y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public float f1037z;

    @Override // E0.InterfaceC0174y
    public final C0.L g(C0.M m10, C0.J j3, long j10) {
        int iO = m10.O(this.f1034A) + m10.O(this.f1036y);
        int iO2 = m10.O(this.B) + m10.O(this.f1037z);
        C0.V vB = j3.b(io.sentry.config.a.g0(-iO, -iO2, j10));
        return m10.m0(io.sentry.config.a.S(j10, vB.l + iO), io.sentry.config.a.R(j10, vB.f871m + iO2), n7.v.l, new C0022k(this, vB, m10, 2));
    }
}
