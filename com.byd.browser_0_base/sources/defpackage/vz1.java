package defpackage;

/* JADX INFO: compiled from: PaymentResponse.java */
/* JADX INFO: loaded from: classes3.dex */
public final class vz1 extends hv2 {
    public static final t50[] h;
    public static final t50 i;
    public String b;
    public String c;
    public ky1 d;
    public String e;
    public iy1 f;
    public nh2 g;

    static {
        t50 t50Var = new t50(56, 0);
        h = new t50[]{t50Var};
        i = t50Var;
    }

    public vz1(int i2) {
        super(56);
    }

    public static vz1 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            vz1 vz1Var = new vz1(e70Var.c(h).b);
            vz1Var.b = e70Var.w(8, false);
            vz1Var.c = e70Var.w(16, false);
            vz1Var.d = ky1.d(e70Var.t(24, true));
            vz1Var.e = e70Var.w(32, true);
            vz1Var.f = iy1.d(e70Var.t(40, false));
            vz1Var.g = nh2.d(e70Var.t(48, true));
            return vz1Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(i);
        qh0VarT.m(this.b, 8, false);
        qh0VarT.m(this.c, 16, false);
        qh0VarT.k(this.d, 24, true);
        qh0VarT.m(this.e, 32, true);
        qh0VarT.k(this.f, 40, false);
        qh0VarT.k(this.g, 48, true);
    }
}
