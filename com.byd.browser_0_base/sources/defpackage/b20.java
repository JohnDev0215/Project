package defpackage;

/* JADX INFO: compiled from: CrossOriginOpenerPolicy.java */
/* JADX INFO: loaded from: classes3.dex */
public final class b20 extends hv2 {
    public static final t50[] g;
    public static final t50 h;
    public int b;
    public String c;
    public int d;
    public String e;
    public int f;

    static {
        t50 t50Var = new t50(40, 0);
        g = new t50[]{t50Var};
        h = t50Var;
    }

    public b20(int i) {
        super(40);
        this.b = 0;
        this.d = 0;
        this.f = 0;
    }

    public static b20 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            b20 b20Var = new b20(e70Var.c(g).b);
            int iP = e70Var.p(8);
            b20Var.b = iP;
            n3.u1(iP);
            b20Var.b = b20Var.b;
            int iP2 = e70Var.p(12);
            b20Var.d = iP2;
            n3.u1(iP2);
            b20Var.d = b20Var.d;
            b20Var.c = e70Var.w(16, true);
            b20Var.e = e70Var.w(24, true);
            int iP3 = e70Var.p(32);
            b20Var.f = iP3;
            n3.u1(iP3);
            b20Var.f = b20Var.f;
            return b20Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(h);
        qh0VarT.b(this.b, 8);
        qh0VarT.b(this.d, 12);
        qh0VarT.m(this.c, 16, true);
        qh0VarT.m(this.e, 24, true);
        qh0VarT.b(this.f, 32);
    }
}
