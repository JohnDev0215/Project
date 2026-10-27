package defpackage;

/* JADX INFO: compiled from: NdefError.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ll1 extends hv2 {
    public static final t50[] d;
    public static final t50 e;
    public int b;
    public String c;

    static {
        t50 t50Var = new t50(24, 0);
        d = new t50[]{t50Var};
        e = t50Var;
    }

    public ll1(int i) {
        super(24);
    }

    public static ll1 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            ll1 ll1Var = new ll1(e70Var.c(d).b);
            int iP = e70Var.p(8);
            ll1Var.b = iP;
            if (!(iP >= 0 && iP <= 5)) {
                throw new q80("Invalid enum value.");
            }
            ll1Var.b = iP;
            ll1Var.c = e70Var.w(16, false);
            return ll1Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(e);
        qh0VarT.b(this.b, 8);
        qh0VarT.m(this.c, 16, false);
    }

    public ll1() {
        this(0);
    }
}
