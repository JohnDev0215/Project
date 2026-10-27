package defpackage;

/* JADX INFO: compiled from: Origin.java */
/* JADX INFO: loaded from: classes3.dex */
public final class et1 extends hv2 {
    public static final t50[] f;
    public static final t50 g;
    public String b;
    public String c;
    public short d;
    public ri3 e;

    static {
        t50 t50Var = new t50(40, 0);
        f = new t50[]{t50Var};
        g = t50Var;
    }

    public et1(int i) {
        super(40);
    }

    public static et1 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            et1 et1Var = new et1(e70Var.c(f).b);
            et1Var.b = e70Var.w(8, false);
            et1Var.c = e70Var.w(16, false);
            et1Var.d = e70Var.v(24);
            et1Var.e = ri3.d(e70Var.t(32, true));
            return et1Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(g);
        qh0VarT.m(this.b, 8, false);
        qh0VarT.m(this.c, 16, false);
        qh0VarT.n(this.d, 24);
        qh0VarT.k(this.e, 32, true);
    }
}
