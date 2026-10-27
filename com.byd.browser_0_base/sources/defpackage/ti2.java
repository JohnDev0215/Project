package defpackage;

/* JADX INFO: compiled from: SerializedBlob.java */
/* JADX INFO: loaded from: classes.dex */
public final class ti2 extends hv2 {
    public static final t50[] f;
    public static final t50 g;
    public String b;
    public String c;
    public long d;
    public bf e;

    static {
        t50 t50Var = new t50(40, 0);
        f = new t50[]{t50Var};
        g = t50Var;
    }

    public ti2(int i) {
        super(40);
    }

    public static ti2 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            ti2 ti2Var = new ti2(e70Var.c(f).b);
            ti2Var.b = e70Var.w(8, false);
            ti2Var.c = e70Var.w(16, false);
            ti2Var.d = e70Var.s(24);
            ti2Var.e = (bf) e70Var.u(32, false, ff.a);
            return ti2Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(g);
        qh0VarT.m(this.b, 8, false);
        qh0VarT.m(this.c, 16, false);
        qh0VarT.f(24, this.d);
        qh0VarT.i(this.e, 32, false, ff.a);
    }
}
