package Q;

import E0.AbstractC0156f;
import E0.InterfaceC0162l;
import E0.InterfaceC0174y;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class D extends g0.n implements InterfaceC0162l, InterfaceC0174y {
    @Override // E0.InterfaceC0174y
    public final C0.L g(C0.M m10, C0.J j3, long j10) {
        float f10 = 0;
        float fR = AbstractC2673a.r(((a1.e) AbstractC0156f.i(this, AbstractC0537w.f6062a)).l, f10);
        C0.V vB = j3.b(j10);
        boolean z6 = this.f14695x && !Float.isNaN(fR) && Float.compare(fR, f10) > 0;
        int iO = Float.isNaN(fR) ? 0 : m10.O(fR);
        int iMax = z6 ? Math.max(vB.l, iO) : vB.l;
        int iMax2 = z6 ? Math.max(vB.f871m, iO) : vB.f871m;
        return m10.m0(iMax, iMax2, n7.v.l, new L.c(iMax, iMax2, vB));
    }
}
