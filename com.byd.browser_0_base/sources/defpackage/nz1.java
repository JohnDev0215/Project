package defpackage;

/* JADX INFO: compiled from: PaymentOptions.java */
/* JADX INFO: loaded from: classes3.dex */
public final class nz1 extends hv2 {
    public static final t50[] g;
    public static final t50 h;
    public boolean b;
    public boolean c;
    public boolean d;
    public boolean e;
    public int f;

    static {
        t50 t50Var = new t50(16, 0);
        g = new t50[]{t50Var};
        h = t50Var;
    }

    public nz1(int i) {
        super(16);
    }

    public static nz1 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            nz1 nz1Var = new nz1(e70Var.c(g).b);
            boolean z = false;
            nz1Var.b = e70Var.d(8, 0);
            nz1Var.c = e70Var.d(8, 1);
            nz1Var.d = e70Var.d(8, 2);
            nz1Var.e = e70Var.d(8, 3);
            int iP = e70Var.p(12);
            nz1Var.f = iP;
            if (iP >= 0 && iP <= 2) {
                z = true;
            }
            if (!z) {
                throw new q80("Invalid enum value.");
            }
            nz1Var.f = iP;
            return nz1Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(h);
        qh0VarT.d(8, 0, this.b);
        qh0VarT.d(8, 1, this.c);
        qh0VarT.d(8, 2, this.d);
        qh0VarT.d(8, 3, this.e);
        qh0VarT.b(this.f, 12);
    }
}
