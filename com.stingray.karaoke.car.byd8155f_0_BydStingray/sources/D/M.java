package D;

import A.C0022k;
import E0.InterfaceC0174y;

/* JADX INFO: loaded from: classes.dex */
public final class M extends g0.n implements InterfaceC0174y {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public L f1040y;

    @Override // E0.InterfaceC0174y
    public final C0.L g(C0.M m10, C0.J j3, long j10) {
        float f10 = 0;
        if (Float.compare(this.f1040y.b(m10.getLayoutDirection()), f10) < 0 || Float.compare(this.f1040y.d(), f10) < 0 || Float.compare(this.f1040y.c(m10.getLayoutDirection()), f10) < 0 || Float.compare(this.f1040y.a(), f10) < 0) {
            throw new IllegalArgumentException("Padding must be non-negative");
        }
        int iO = m10.O(this.f1040y.c(m10.getLayoutDirection())) + m10.O(this.f1040y.b(m10.getLayoutDirection()));
        int iO2 = m10.O(this.f1040y.a()) + m10.O(this.f1040y.d());
        C0.V vB = j3.b(io.sentry.config.a.g0(-iO, -iO2, j10));
        return m10.m0(io.sentry.config.a.S(j10, vB.l + iO), io.sentry.config.a.R(j10, vB.f871m + iO2), n7.v.l, new C0022k(vB, m10, this, 3));
    }
}
