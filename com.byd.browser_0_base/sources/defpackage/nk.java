package defpackage;

/* JADX INFO: compiled from: CableAuthentication.java */
/* JADX INFO: loaded from: classes.dex */
public final class nk extends hv2 {
    public static final t50[] h;
    public static final t50 i;
    public byte b;
    public byte[] c;
    public byte[] d;
    public byte[] e;
    public byte[] f;
    public byte[] g;

    static {
        t50 t50Var = new t50(56, 0);
        h = new t50[]{t50Var};
        i = t50Var;
    }

    public nk(int i2) {
        super(56);
    }

    public static nk d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            nk nkVar = new nk(e70Var.c(h).b);
            nkVar.b = e70Var.e(8);
            nkVar.c = e70Var.f(16, 1, 16);
            nkVar.d = e70Var.f(24, 1, 16);
            nkVar.e = e70Var.f(32, 1, 32);
            nkVar.f = e70Var.f(40, 1, -1);
            nkVar.g = e70Var.f(48, 1, -1);
            return nkVar;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(i);
        qh0VarT.b.b.put(qh0VarT.a + 8, this.b);
        qh0VarT.c(16, 1, 16, this.c);
        qh0VarT.c(24, 1, 16, this.d);
        qh0VarT.c(32, 1, 32, this.e);
        qh0VarT.c(40, 1, -1, this.f);
        qh0VarT.c(48, 1, -1, this.g);
    }
}
