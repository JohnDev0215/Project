package defpackage;

/* JADX INFO: compiled from: CommonCredentialInfo.java */
/* JADX INFO: loaded from: classes.dex */
public final class mv extends hv2 {
    public static final t50[] f;
    public static final t50 g;
    public String b;
    public byte[] c;
    public byte[] d;
    public byte[] e;

    static {
        t50 t50Var = new t50(40, 0);
        f = new t50[]{t50Var};
        g = t50Var;
    }

    public mv(int i) {
        super(40);
    }

    public static mv d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            mv mvVar = new mv(e70Var.c(f).b);
            mvVar.b = e70Var.w(8, false);
            mvVar.c = e70Var.f(16, 0, -1);
            mvVar.d = e70Var.f(24, 0, -1);
            mvVar.e = e70Var.f(32, 0, -1);
            return mvVar;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(g);
        qh0VarT.m(this.b, 8, false);
        qh0VarT.c(16, 0, -1, this.c);
        qh0VarT.c(24, 0, -1, this.d);
        qh0VarT.c(32, 0, -1, this.e);
    }
}
