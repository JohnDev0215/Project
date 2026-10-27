package defpackage;

/* JADX INFO: compiled from: CorsErrorStatus.java */
/* JADX INFO: loaded from: classes3.dex */
public final class w00 extends hv2 {
    public static final t50[] h;
    public static final t50 i;
    public int b;
    public String c;
    public int d;
    public int e;
    public boolean f;
    public ri3 g;

    static {
        t50 t50Var = new t50(40, 0);
        h = new t50[]{t50Var};
        i = t50Var;
    }

    public w00(int i2) {
        super(40);
        this.d = 3;
        this.e = 3;
        this.f = false;
    }

    public static w00 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            w00 w00Var = new w00(e70Var.c(h).b);
            int iP = e70Var.p(8);
            w00Var.b = iP;
            if (!(iP >= 0 && iP <= 26)) {
                throw new q80("Invalid enum value.");
            }
            w00Var.b = iP;
            int iP2 = e70Var.p(12);
            w00Var.d = iP2;
            n3.v1(iP2);
            w00Var.d = w00Var.d;
            w00Var.c = e70Var.w(16, false);
            int iP3 = e70Var.p(24);
            w00Var.e = iP3;
            n3.v1(iP3);
            w00Var.e = w00Var.e;
            w00Var.f = e70Var.d(28, 0);
            w00Var.g = ri3.d(e70Var.t(32, false));
            return w00Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(i);
        qh0VarT.b(this.b, 8);
        qh0VarT.b(this.d, 12);
        qh0VarT.m(this.c, 16, false);
        qh0VarT.b(this.e, 24);
        qh0VarT.d(28, 0, this.f);
        qh0VarT.k(this.g, 32, false);
    }
}
