package U;

import E0.Y;
import T.B0;
import T.C0733c;
import T.z0;
import a.AbstractC0904a;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class n extends C {
    public static final n c = new n(0, 2, 1);

    @Override // U.C
    public final void a(E5.z zVar, P p10, B0 b02, Y y10) {
        z0 z0Var = (z0) zVar.g(1);
        C0733c c0733c = (C0733c) zVar.g(0);
        b02.d();
        c0733c.getClass();
        b02.t(z0Var, z0Var.d(c0733c));
        b02.j();
    }

    @Override // U.C
    public final String c(int i7) {
        return AbstractC0904a.B(i7, 0) ? "anchor" : AbstractC0904a.B(i7, 1) ? "from" : super.c(i7);
    }
}
