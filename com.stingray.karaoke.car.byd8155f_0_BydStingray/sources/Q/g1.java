package Q;

import A.u1;
import E0.InterfaceC0174y;
import S8.AbstractC0727y;
import x.C2629d;

/* JADX INFO: loaded from: classes.dex */
public final class g1 extends g0.n implements InterfaceC0174y {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f5894A;
    public C2629d B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public C2629d f5895C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public float f5896D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public float f5897E;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public C.k f5898y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f5899z;

    @Override // E0.InterfaceC0174y
    public final C0.L g(C0.M m10, C0.J j3, long j10) {
        boolean z6 = false;
        float fY = m10.y(this.f5894A ? S.n.f7706a : ((j3.c(a1.a.h(j10)) != 0 && j3.V(a1.a.g(j10)) != 0) || this.f5899z) ? androidx.compose.material3.d.f11541a : androidx.compose.material3.d.f11542b);
        C2629d c2629d = this.f5895C;
        int iFloatValue = (int) (c2629d != null ? ((Number) c2629d.d()).floatValue() : fY);
        if (iFloatValue >= 0 && iFloatValue >= 0) {
            z6 = true;
        }
        if (!z6) {
            w0.c.e0("width(" + iFloatValue + ") and height(" + iFloatValue + ") must be >= 0");
            throw null;
        }
        C0.V vB = j3.b(io.sentry.config.a.U(iFloatValue, iFloatValue, iFloatValue, iFloatValue));
        float fY2 = m10.y((androidx.compose.material3.d.d - m10.r0(fY)) / 2.0f);
        float fY3 = m10.y((androidx.compose.material3.d.c - androidx.compose.material3.d.f11541a) - androidx.compose.material3.d.f11543e);
        boolean z10 = this.f5894A;
        if (z10 && this.f5899z) {
            fY2 = fY3 - m10.y(S.n.f7708e);
        } else if (z10 && !this.f5899z) {
            fY2 = m10.y(S.n.f7708e);
        } else if (this.f5899z) {
            fY2 = fY3;
        }
        C2629d c2629d2 = this.f5895C;
        Float f10 = c2629d2 != null ? (Float) c2629d2.f23493e.getValue() : null;
        if (f10 == null || f10.floatValue() != fY) {
            AbstractC0727y.w(v0(), null, null, new d1(this, fY, null), 3);
        }
        C2629d c2629d3 = this.B;
        Float f11 = c2629d3 != null ? (Float) c2629d3.f23493e.getValue() : null;
        if (f11 == null || f11.floatValue() != fY2) {
            AbstractC0727y.w(v0(), null, null, new e1(this, fY2, null), 3);
        }
        if (Float.isNaN(this.f5897E) && Float.isNaN(this.f5896D)) {
            this.f5897E = fY;
            this.f5896D = fY2;
        }
        return m10.m0(iFloatValue, iFloatValue, n7.v.l, new u1(vB, this, fY2));
    }

    @Override // g0.n
    public final boolean w0() {
        return false;
    }

    @Override // g0.n
    public final void z0() {
        AbstractC0727y.w(v0(), null, null, new f1(this, null), 3);
    }
}
