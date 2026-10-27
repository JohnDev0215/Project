package defpackage;

/* JADX INFO: compiled from: PayerDetail.java */
/* JADX INFO: loaded from: classes3.dex */
public final class iy1 extends hv2 {
    public static final t50[] e;
    public static final t50 f;
    public String b;
    public String c;
    public String d;

    static {
        t50 t50Var = new t50(32, 0);
        e = new t50[]{t50Var};
        f = t50Var;
    }

    public iy1(int i) {
        super(32);
    }

    public static iy1 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            iy1 iy1Var = new iy1(e70Var.c(e).b);
            iy1Var.b = e70Var.w(8, true);
            iy1Var.c = e70Var.w(16, true);
            iy1Var.d = e70Var.w(24, true);
            return iy1Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(f);
        qh0VarT.m(this.b, 8, true);
        qh0VarT.m(this.c, 16, true);
        qh0VarT.m(this.d, 24, true);
    }
}
