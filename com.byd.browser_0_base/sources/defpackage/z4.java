package defpackage;

import org.chromium.gfx.mojom.Rect;

/* JADX INFO: compiled from: AndroidOverlayConfig.java */
/* JADX INFO: loaded from: classes3.dex */
public final class z4 extends hv2 {
    public static final t50[] f;
    public static final t50 g;
    public ri3 b;
    public Rect c;
    public boolean d;
    public boolean e;

    static {
        t50 t50Var = new t50(32, 0);
        f = new t50[]{t50Var};
        g = t50Var;
    }

    public z4(int i) {
        super(32);
    }

    public static z4 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            z4 z4Var = new z4(e70Var.c(f).b);
            z4Var.b = ri3.d(e70Var.t(8, false));
            z4Var.c = Rect.d(e70Var.t(16, false));
            z4Var.d = e70Var.d(24, 0);
            z4Var.e = e70Var.d(24, 1);
            return z4Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(g);
        qh0VarT.k(this.b, 8, false);
        qh0VarT.k(this.c, 16, false);
        qh0VarT.d(24, 0, this.d);
        qh0VarT.d(24, 1, this.e);
    }
}
