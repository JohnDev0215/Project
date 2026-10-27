package defpackage;

/* JADX INFO: compiled from: PayerErrors.java */
/* JADX INFO: loaded from: classes3.dex */
public final class jy1 extends hv2 {
    public static final t50[] e;
    public static final t50 f;
    public String b;
    public String c;
    public String d;

    static {
        t50 t50Var = new t50(32, 0);
        e = new t50[]{t50Var};
        f = t50Var;
    }

    public jy1(int i) {
        super(32);
    }

    public static jy1 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            jy1 jy1Var = new jy1(e70Var.c(e).b);
            jy1Var.b = e70Var.w(8, false);
            jy1Var.c = e70Var.w(16, false);
            jy1Var.d = e70Var.w(24, false);
            return jy1Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(f);
        qh0VarT.m(this.b, 8, false);
        qh0VarT.m(this.c, 16, false);
        qh0VarT.m(this.d, 24, false);
    }
}
