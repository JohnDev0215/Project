package defpackage;

/* JADX INFO: compiled from: PaymentValidationErrors.java */
/* JADX INFO: loaded from: classes3.dex */
public final class xz1 extends hv2 {
    public static final t50[] e;
    public static final t50 f;
    public String b;
    public jy1 c;
    public d4 d;

    static {
        t50 t50Var = new t50(32, 0);
        e = new t50[]{t50Var};
        f = t50Var;
    }

    public xz1(int i) {
        super(32);
    }

    public static xz1 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            xz1 xz1Var = new xz1(e70Var.c(e).b);
            xz1Var.b = e70Var.w(8, false);
            xz1Var.c = jy1.d(e70Var.t(16, false));
            xz1Var.d = d4.d(e70Var.t(24, false));
            return xz1Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(f);
        qh0VarT.m(this.b, 8, false);
        qh0VarT.k(this.c, 16, false);
        qh0VarT.k(this.d, 24, false);
    }
}
