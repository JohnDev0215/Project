package U;

import E0.Y;
import H7.H;
import T.B0;
import T.C0735d;
import T.C0754m0;
import T.v0;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class x extends C {
    public static final x c = new x(1, 0, 2);

    @Override // U.C
    public final void a(E5.z zVar, P p10, B0 b02, Y y10) {
        int iF = zVar.f(0);
        int iO = b02.o();
        int i7 = b02.f8174v;
        int iE = b02.E(b02.f8158b, b02.p(i7));
        int iF2 = b02.f(b02.f8158b, b02.p(i7 + 1));
        for (int iMax = Math.max(iE, iF2 - iF); iMax < iF2; iMax++) {
            Object obj = b02.c[b02.g(iMax)];
            if (obj instanceof v0) {
                y10.h(iO - iMax, -1, -1, ((v0) obj).f8385a);
            } else if (obj instanceof C0754m0) {
                ((C0754m0) obj).d();
            }
        }
        C0735d.T(iF > 0);
        int i10 = b02.f8174v;
        int iE2 = b02.E(b02.f8158b, b02.p(i10));
        int iF3 = b02.f(b02.f8158b, b02.p(i10 + 1)) - iF;
        C0735d.T(iF3 >= iE2);
        b02.C(iF3, iF, i10);
        int i11 = b02.f8163i;
        if (i11 >= iE2) {
            b02.f8163i = i11 - iF;
        }
    }

    @Override // U.C
    public final String b(int i7) {
        return H.y(i7, 0) ? "count" : super.b(i7);
    }
}
