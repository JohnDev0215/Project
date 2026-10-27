package defpackage;

/* JADX INFO: compiled from: CrossOriginEmbedderPolicy.java */
/* JADX INFO: loaded from: classes3.dex */
public final class a20 extends hv2 {
    public static final t50[] f;
    public static final t50 g;
    public int b;
    public String c;
    public int d;
    public String e;

    static {
        t50 t50Var = new t50(32, 0);
        f = new t50[]{t50Var};
        g = t50Var;
    }

    public a20(int i) {
        super(32);
        this.b = 0;
        this.d = 0;
    }

    public static a20 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            a20 a20Var = new a20(e70Var.c(f).b);
            int iP = e70Var.p(8);
            a20Var.b = iP;
            boolean z = false;
            if (!(iP >= 0 && iP <= 2)) {
                throw new q80("Invalid enum value.");
            }
            a20Var.b = iP;
            int iP2 = e70Var.p(12);
            a20Var.d = iP2;
            if (iP2 >= 0 && iP2 <= 2) {
                z = true;
            }
            if (!z) {
                throw new q80("Invalid enum value.");
            }
            a20Var.d = iP2;
            a20Var.c = e70Var.w(16, true);
            a20Var.e = e70Var.w(24, true);
            return a20Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(g);
        qh0VarT.b(this.b, 8);
        qh0VarT.b(this.d, 12);
        qh0VarT.m(this.c, 16, true);
        qh0VarT.m(this.e, 24, true);
    }
}
