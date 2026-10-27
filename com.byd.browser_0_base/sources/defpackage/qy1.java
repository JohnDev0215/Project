package defpackage;

/* JADX INFO: compiled from: PaymentCredentialInstrument.java */
/* JADX INFO: loaded from: classes.dex */
public final class qy1 extends hv2 {
    public static final t50[] e;
    public static final t50 f;
    public String b;
    public qj3 c;
    public boolean d;

    static {
        t50 t50Var = new t50(32, 0);
        e = new t50[]{t50Var};
        f = t50Var;
    }

    public qy1(int i) {
        super(32);
    }

    public static qy1 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            qy1 qy1Var = new qy1(e70Var.c(e).b);
            qy1Var.b = e70Var.w(8, false);
            qy1Var.c = qj3.d(e70Var.t(16, false));
            qy1Var.d = e70Var.d(24, 0);
            return qy1Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(f);
        qh0VarT.m(this.b, 8, false);
        qh0VarT.k(this.c, 16, false);
        qh0VarT.d(24, 0, this.d);
    }
}
