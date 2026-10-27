package defpackage;

/* JADX INFO: compiled from: PublicKeyCredentialUserEntity.java */
/* JADX INFO: loaded from: classes.dex */
public final class m72 extends hv2 {
    public static final t50[] e;
    public static final t50 f;
    public byte[] b;
    public String c;
    public String d;

    static {
        t50 t50Var = new t50(32, 0);
        e = new t50[]{t50Var};
        f = t50Var;
    }

    public m72(int i) {
        super(32);
    }

    public static m72 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            m72 m72Var = new m72(e70Var.c(e).b);
            m72Var.b = e70Var.f(8, 0, -1);
            m72Var.c = e70Var.w(16, false);
            m72Var.d = e70Var.w(24, false);
            return m72Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(f);
        qh0VarT.c(8, 0, -1, this.b);
        qh0VarT.m(this.c, 16, false);
        qh0VarT.m(this.d, 24, false);
    }
}
