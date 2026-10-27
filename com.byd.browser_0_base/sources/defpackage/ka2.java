package defpackage;

/* JADX INFO: compiled from: RelatedApplication.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ka2 extends hv2 {
    public static final t50[] f;
    public static final t50 g;
    public String b;
    public String c;
    public String d;
    public String e;

    static {
        t50 t50Var = new t50(40, 0);
        f = new t50[]{t50Var};
        g = t50Var;
    }

    public ka2(int i) {
        super(40);
    }

    public static ka2 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            ka2 ka2Var = new ka2(e70Var.c(f).b);
            ka2Var.b = e70Var.w(8, false);
            ka2Var.c = e70Var.w(16, true);
            ka2Var.d = e70Var.w(24, true);
            ka2Var.e = e70Var.w(32, true);
            return ka2Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(g);
        qh0VarT.m(this.b, 8, false);
        qh0VarT.m(this.c, 16, true);
        qh0VarT.m(this.d, 24, true);
        qh0VarT.m(this.e, 32, true);
    }

    public ka2() {
        this(0);
    }
}
