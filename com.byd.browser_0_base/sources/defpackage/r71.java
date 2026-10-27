package defpackage;

/* JADX INFO: compiled from: LinkHeader.java */
/* JADX INFO: loaded from: classes3.dex */
public final class r71 extends hv2 {
    public static final t50[] h;
    public static final t50 i;
    public qj3 b;
    public int c;
    public int d;
    public int e;
    public int f;
    public String g;

    static {
        t50 t50Var = new t50(40, 0);
        h = new t50[]{t50Var};
        i = t50Var;
    }

    public r71(int i2) {
        super(40);
        this.d = 0;
        this.e = 0;
        this.f = 1;
    }

    public static r71 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            r71 r71Var = new r71(e70Var.c(h).b);
            boolean z = false;
            r71Var.b = qj3.d(e70Var.t(8, false));
            int iP = e70Var.p(16);
            r71Var.c = iP;
            if (!(iP >= 0 && iP <= 3)) {
                throw new q80("Invalid enum value.");
            }
            r71Var.c = iP;
            int iP2 = e70Var.p(20);
            r71Var.d = iP2;
            if (!(iP2 >= 0 && iP2 <= 5)) {
                throw new q80("Invalid enum value.");
            }
            r71Var.d = iP2;
            int iP3 = e70Var.p(24);
            r71Var.e = iP3;
            if (!(iP3 >= 0 && iP3 <= 2)) {
                throw new q80("Invalid enum value.");
            }
            r71Var.e = iP3;
            int iP4 = e70Var.p(28);
            r71Var.f = iP4;
            if (iP4 >= 0 && iP4 <= 2) {
                z = true;
            }
            if (!z) {
                throw new q80("Invalid enum value.");
            }
            r71Var.f = iP4;
            r71Var.g = e70Var.w(32, true);
            return r71Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(i);
        qh0VarT.k(this.b, 8, false);
        qh0VarT.b(this.c, 16);
        qh0VarT.b(this.d, 20);
        qh0VarT.b(this.e, 24);
        qh0VarT.b(this.f, 28);
        qh0VarT.m(this.g, 32, true);
    }
}
