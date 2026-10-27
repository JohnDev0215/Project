package defpackage;

/* JADX INFO: compiled from: CspSource.java */
/* JADX INFO: loaded from: classes3.dex */
public final class d20 extends hv2 {
    public static final t50[] h;
    public static final t50 i;
    public String b;
    public String c;
    public int d;
    public String e;
    public boolean f;
    public boolean g;

    static {
        t50 t50Var = new t50(40, 0);
        h = new t50[]{t50Var};
        i = t50Var;
    }

    public d20(int i2) {
        super(40);
        this.d = -1;
        this.f = false;
        this.g = false;
    }

    public static d20 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            d20 d20Var = new d20(e70Var.c(h).b);
            d20Var.b = e70Var.w(8, false);
            d20Var.c = e70Var.w(16, false);
            d20Var.d = e70Var.p(24);
            d20Var.f = e70Var.d(28, 0);
            d20Var.g = e70Var.d(28, 1);
            d20Var.e = e70Var.w(32, false);
            return d20Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(i);
        qh0VarT.m(this.b, 8, false);
        qh0VarT.m(this.c, 16, false);
        qh0VarT.b(this.d, 24);
        qh0VarT.d(28, 0, this.f);
        qh0VarT.d(28, 1, this.g);
        qh0VarT.m(this.e, 32, false);
    }
}
