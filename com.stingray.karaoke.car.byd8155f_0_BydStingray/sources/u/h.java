package U;

import E0.Y;
import T.B0;
import a.AbstractC0904a;
import l3.P;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class h extends C {
    public static final h c = new h(0, 2, 1);

    @Override // U.C
    public final void a(E5.z zVar, P p10, B0 b02, Y y10) {
        ((InterfaceC2782k) zVar.g(0)).b((T.r) zVar.g(1));
    }

    @Override // U.C
    public final String c(int i7) {
        return AbstractC0904a.B(i7, 0) ? "anchor" : AbstractC0904a.B(i7, 1) ? "composition" : super.c(i7);
    }
}
