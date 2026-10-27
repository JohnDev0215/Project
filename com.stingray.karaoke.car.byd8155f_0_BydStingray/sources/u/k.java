package U;

import E0.Y;
import T.B0;
import T.C0733c;
import a.AbstractC0904a;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class k extends C {
    public static final k c;

    static {
        int i7 = 1;
        c = new k(0, i7, i7);
    }

    @Override // U.C
    public final void a(E5.z zVar, P p10, B0 b02, Y y10) {
        C0733c c0733c = (C0733c) zVar.g(0);
        c0733c.getClass();
        b02.k(b02.c(c0733c));
    }

    @Override // U.C
    public final String c(int i7) {
        return AbstractC0904a.B(i7, 0) ? "anchor" : super.c(i7);
    }
}
