package U;

import E0.Y;
import H7.H;
import T.B0;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class A extends C {
    public static final A c = new A(1, 0, 2);

    @Override // U.C
    public final void a(E5.z zVar, P p10, B0 b02, Y y10) {
        int iF = zVar.f(0);
        for (int i7 = 0; i7 < iF; i7++) {
            p10.I();
        }
    }

    @Override // U.C
    public final String b(int i7) {
        return H.y(i7, 0) ? "count" : super.b(i7);
    }
}
