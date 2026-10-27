package defpackage;

/* JADX INFO: compiled from: PaymentShippingOption.java */
/* JADX INFO: loaded from: classes3.dex */
public final class wz1 extends hv2 {
    public static final t50[] f;
    public static final t50 g;
    public String b;
    public String c;
    public ry1 d;
    public boolean e;

    static {
        t50 t50Var = new t50(40, 0);
        f = new t50[]{t50Var};
        g = t50Var;
    }

    public wz1(int i) {
        super(40);
    }

    public static wz1 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            wz1 wz1Var = new wz1(e70Var.c(f).b);
            wz1Var.b = e70Var.w(8, false);
            wz1Var.c = e70Var.w(16, false);
            wz1Var.d = ry1.d(e70Var.t(24, false));
            wz1Var.e = e70Var.d(32, 0);
            return wz1Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(g);
        qh0VarT.m(this.b, 8, false);
        qh0VarT.m(this.c, 16, false);
        qh0VarT.k(this.d, 24, false);
        qh0VarT.d(32, 0, this.e);
    }

    public wz1() {
        this(0);
    }
}
