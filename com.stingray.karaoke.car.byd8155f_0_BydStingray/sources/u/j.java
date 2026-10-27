package U;

import E0.Y;
import T.B0;
import T.C0735d;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class j extends C {
    public static final j c;

    static {
        int i7 = 0;
        c = new j(i7, i7, 3);
    }

    @Override // U.C
    public final void a(E5.z zVar, P p10, B0 b02, Y y10) {
        A7.m.d(p10, "null cannot be cast to non-null type androidx.compose.runtime.Applier<kotlin.Any?>");
        while (true) {
            int i7 = b02.f8174v;
            if ((i7 < 0 && b02.f8173u > 0) || i7 == 0) {
                break;
            }
            b02.D();
            if (C0735d.o(b02.f8158b, b02.p(b02.f8174v))) {
                p10.I();
            }
            b02.i();
        }
        b02.i();
    }
}
