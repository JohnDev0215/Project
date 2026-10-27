package defpackage;

/* JADX INFO: compiled from: RectF.java */
/* JADX INFO: loaded from: classes3.dex */
public final class y92 extends hv2 {
    public static final t50[] f;
    public static final t50 g;
    public float b;
    public float c;
    public float d;
    public float e;

    static {
        t50 t50Var = new t50(24, 0);
        f = new t50[]{t50Var};
        g = t50Var;
    }

    public y92(int i) {
        super(24);
    }

    public static y92 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            y92 y92Var = new y92(e70Var.c(f).b);
            y92Var.b = e70Var.n(8);
            y92Var.c = e70Var.n(12);
            y92Var.d = e70Var.n(16);
            y92Var.e = e70Var.n(20);
            return y92Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(g);
        qh0VarT.a(this.b, 8);
        qh0VarT.a(this.c, 12);
        qh0VarT.a(this.d, 16);
        qh0VarT.a(this.e, 20);
    }

    public y92() {
        this(0);
    }
}
