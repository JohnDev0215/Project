package defpackage;

/* JADX INFO: compiled from: ContentSecurityPolicyHeader.java */
/* JADX INFO: loaded from: classes3.dex */
public final class vy extends hv2 {
    public static final t50[] e;
    public static final t50 f;
    public String b;
    public int c;
    public int d;

    static {
        t50 t50Var = new t50(24, 0);
        e = new t50[]{t50Var};
        f = t50Var;
    }

    public vy(int i) {
        super(24);
        this.c = 1;
        this.d = 0;
    }

    public static vy d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            vy vyVar = new vy(e70Var.c(e).b);
            boolean z = false;
            vyVar.b = e70Var.w(8, false);
            int iP = e70Var.p(16);
            vyVar.c = iP;
            if (!(iP >= 0 && iP <= 1)) {
                throw new q80("Invalid enum value.");
            }
            vyVar.c = iP;
            int iP2 = e70Var.p(20);
            vyVar.d = iP2;
            if (iP2 >= 0 && iP2 <= 1) {
                z = true;
            }
            if (!z) {
                throw new q80("Invalid enum value.");
            }
            vyVar.d = iP2;
            return vyVar;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(f);
        qh0VarT.m(this.b, 8, false);
        qh0VarT.b(this.c, 16);
        qh0VarT.b(this.d, 20);
    }
}
