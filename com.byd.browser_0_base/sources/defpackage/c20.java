package defpackage;

/* JADX INFO: compiled from: CspHashSource.java */
/* JADX INFO: loaded from: classes3.dex */
public final class c20 extends hv2 {
    public static final t50[] d;
    public static final t50 e;
    public int b;
    public byte[] c;

    static {
        t50 t50Var = new t50(24, 0);
        d = new t50[]{t50Var};
        e = t50Var;
    }

    public c20(int i) {
        super(24);
    }

    public static c20 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            c20 c20Var = new c20(e70Var.c(d).b);
            int iP = e70Var.p(8);
            c20Var.b = iP;
            boolean z = true;
            if (iP != 0 && iP != 1 && iP != 2 && iP != 4) {
                z = false;
            }
            if (!z) {
                throw new q80("Invalid enum value.");
            }
            c20Var.b = iP;
            c20Var.c = e70Var.f(16, 0, -1);
            return c20Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(e);
        qh0VarT.b(this.b, 8);
        qh0VarT.c(16, 0, -1, this.c);
    }
}
