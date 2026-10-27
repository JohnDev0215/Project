package defpackage;

/* JADX INFO: compiled from: SecurePaymentConfirmationResponse.java */
/* JADX INFO: loaded from: classes3.dex */
public final class nh2 extends hv2 {
    public static final t50[] f;
    public static final t50 g;
    public mv b;
    public byte[] c;
    public int d;
    public byte[] e;

    static {
        t50 t50Var = new t50(40, 0);
        f = new t50[]{t50Var};
        g = t50Var;
    }

    public nh2(int i) {
        super(40);
    }

    public static nh2 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            nh2 nh2Var = new nh2(e70Var.c(f).b);
            nh2Var.b = mv.d(e70Var.t(8, false));
            nh2Var.c = e70Var.f(16, 0, -1);
            int iP = e70Var.p(24);
            nh2Var.d = iP;
            n3.t1(iP);
            nh2Var.d = nh2Var.d;
            nh2Var.e = e70Var.f(32, 1, -1);
            return nh2Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(g);
        qh0VarT.k(this.b, 8, false);
        qh0VarT.c(16, 0, -1, this.c);
        qh0VarT.b(this.d, 24);
        qh0VarT.c(32, 1, -1, this.e);
    }
}
