package S3;

import C0.J;
import C0.L;
import C0.M;
import C0.V;
import C0.X;
import E0.InterfaceC0174y;
import H7.H;
import n7.v;

/* JADX INFO: loaded from: classes.dex */
public final class k extends g0.n implements InterfaceC0174y {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f7950y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f7951z;

    @Override // E0.InterfaceC0174y
    public final L g(M m10, J j3, long j10) {
        long jE;
        A7.m.f(j3, "measurable");
        long jP = io.sentry.config.a.P(j10, H.c(this.f7950y, this.f7951z));
        if (a1.a.g(j10) == Integer.MAX_VALUE && a1.a.h(j10) != Integer.MAX_VALUE) {
            int i7 = (int) (jP >> 32);
            int i10 = (this.f7951z * i7) / this.f7950y;
            jE = io.sentry.config.a.e(i7, i7, i10, i10);
        } else if (a1.a.h(j10) != Integer.MAX_VALUE || a1.a.g(j10) == Integer.MAX_VALUE) {
            int i11 = (int) (jP >> 32);
            int i12 = (int) (jP & 4294967295L);
            jE = io.sentry.config.a.e(i11, i11, i12, i12);
        } else {
            int i13 = (int) (jP & 4294967295L);
            int i14 = (this.f7950y * i13) / this.f7951z;
            jE = io.sentry.config.a.e(i14, i14, i13, i13);
        }
        V vB = j3.b(jE);
        return m10.m0(vB.l, vB.f871m, v.l, new X(vB, 9));
    }
}
