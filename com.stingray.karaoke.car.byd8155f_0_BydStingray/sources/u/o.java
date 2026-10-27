package U;

import E0.Y;
import T.B0;
import T.C0733c;
import T.C0735d;
import T.z0;
import a.AbstractC0904a;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class o extends C {
    public static final o c = new o(0, 3, 1);

    @Override // U.C
    public final void a(E5.z zVar, P p10, B0 b02, Y y10) {
        z0 z0Var = (z0) zVar.g(1);
        C0733c c0733c = (C0733c) zVar.g(0);
        C0802c c0802c = (C0802c) zVar.g(2);
        B0 b0R = z0Var.r();
        try {
            if (!c0802c.f8952e.S()) {
                C0735d.y("FixupList has pending fixup operations that were not realized. Were there mismatched insertNode() and endNodeInsert() calls?");
                throw null;
            }
            c0802c.d.R(p10, b0R, y10);
            b0R.e(true);
            b02.d();
            c0733c.getClass();
            b02.t(z0Var, z0Var.d(c0733c));
            b02.j();
        } catch (Throwable th) {
            b0R.e(false);
            throw th;
        }
    }

    @Override // U.C
    public final String c(int i7) {
        return AbstractC0904a.B(i7, 0) ? "anchor" : AbstractC0904a.B(i7, 1) ? "from" : AbstractC0904a.B(i7, 2) ? "fixups" : super.c(i7);
    }
}
