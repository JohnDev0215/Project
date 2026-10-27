package U;

import E0.Y;
import T.B0;
import a.AbstractC0904a;
import l3.P;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class z extends C {
    public static final z c = new z(0, 2, 1);

    @Override // U.C
    public final void a(E5.z zVar, P p10, B0 b02, Y y10) {
        ((InterfaceC2785n) zVar.g(1)).invoke(p10.q(), zVar.g(0));
    }

    @Override // U.C
    public final String c(int i7) {
        return AbstractC0904a.B(i7, 0) ? "value" : AbstractC0904a.B(i7, 1) ? "block" : super.c(i7);
    }
}
