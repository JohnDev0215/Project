package L;

import C0.J;
import C0.L;
import C0.M;
import C0.V;
import E0.AbstractC0164n;
import E0.InterfaceC0174y;
import E0.n0;
import l0.EnumC1726q;
import l0.InterfaceC1712c;
import y0.C2722B;
import y0.C2730h;
import y0.EnumC2731i;
import y0.v;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class d extends AbstractC0164n implements InterfaceC0174y, n0, InterfaceC1712c {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public InterfaceC2772a f3872A;
    public boolean B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final C2722B f3873C;

    public d(InterfaceC2772a interfaceC2772a) {
        this.f3872A = interfaceC2772a;
        b bVar = new b(this, null);
        C2730h c2730h = v.f24101a;
        C2722B c2722b = new C2722B(null, null, null, bVar);
        H0(c2722b);
        this.f3873C = c2722b;
    }

    @Override // l0.InterfaceC1712c
    public final void D(EnumC1726q enumC1726q) {
        this.B = enumC1726q.a();
    }

    @Override // E0.n0
    public final void R(C2730h c2730h, EnumC2731i enumC2731i, long j3) {
        this.f3873C.R(c2730h, enumC2731i, j3);
    }

    @Override // E0.n0
    public final void e0() {
        this.f3873C.e0();
    }

    @Override // E0.InterfaceC0174y
    public final L g(M m10, J j3, long j10) {
        int iO = m10.O(androidx.compose.foundation.text.handwriting.a.f11505a);
        int iO2 = m10.O(androidx.compose.foundation.text.handwriting.a.f11506b);
        int i7 = iO2 * 2;
        int i10 = iO * 2;
        V vB = j3.b(io.sentry.config.a.g0(i7, i10, j10));
        int i11 = vB.f871m - i10;
        return m10.m0(vB.l - i7, i11, n7.v.l, new c(iO2, iO, 0, vB));
    }
}
