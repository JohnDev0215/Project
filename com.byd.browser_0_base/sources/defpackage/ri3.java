package defpackage;

/* JADX INFO: compiled from: UnguessableToken.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ri3 extends hv2 {
    public static final t50[] d;
    public static final t50 e;
    public long b;
    public long c;

    static {
        t50 t50Var = new t50(24, 0);
        d = new t50[]{t50Var};
        e = t50Var;
    }

    public ri3(int i) {
        super(24);
    }

    public static ri3 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            ri3 ri3Var = new ri3(e70Var.c(d).b);
            ri3Var.b = e70Var.s(8);
            ri3Var.c = e70Var.s(16);
            return ri3Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(e);
        qh0VarT.f(8, this.b);
        qh0VarT.f(16, this.c);
    }
}
