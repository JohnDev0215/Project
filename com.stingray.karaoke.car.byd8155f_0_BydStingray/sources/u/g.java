package U;

import E0.Y;
import T.B0;
import a.AbstractC0904a;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class g extends C {
    public static final g c = new g(0, 1, 1);

    @Override // U.C
    public final void a(E5.z zVar, P p10, B0 b02, Y y10) {
        A7.m.d(p10, "null cannot be cast to non-null type androidx.compose.runtime.Applier<kotlin.Any?>");
        for (Object obj : (Object[]) zVar.g(0)) {
            p10.n(obj);
        }
    }

    @Override // U.C
    public final String c(int i7) {
        return AbstractC0904a.B(i7, 0) ? "nodes" : super.c(i7);
    }
}
