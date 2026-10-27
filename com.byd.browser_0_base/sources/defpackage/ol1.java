package defpackage;

/* JADX INFO: compiled from: NdefRecord.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ol1 extends hv2 {
    public static final t50[] j;
    public static final t50 k;
    public int b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public byte[] h;
    public ml1 i;

    static {
        t50 t50Var = new t50(72, 0);
        j = new t50[]{t50Var};
        k = t50Var;
    }

    public ol1(int i) {
        super(72);
    }

    public static ol1 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            ol1 ol1Var = new ol1(e70Var.c(j).b);
            int iP = e70Var.p(8);
            ol1Var.b = iP;
            if (!(iP >= 0 && iP <= 2)) {
                throw new q80("Invalid enum value.");
            }
            ol1Var.b = iP;
            ol1Var.c = e70Var.w(16, false);
            ol1Var.d = e70Var.w(24, true);
            ol1Var.e = e70Var.w(32, true);
            ol1Var.f = e70Var.w(40, true);
            ol1Var.g = e70Var.w(48, true);
            ol1Var.h = e70Var.f(56, 0, -1);
            ol1Var.i = ml1.d(e70Var.t(64, true));
            return ol1Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(k);
        qh0VarT.b(this.b, 8);
        qh0VarT.m(this.c, 16, false);
        qh0VarT.m(this.d, 24, true);
        qh0VarT.m(this.e, 32, true);
        qh0VarT.m(this.f, 40, true);
        qh0VarT.m(this.g, 48, true);
        qh0VarT.c(56, 0, -1, this.h);
        qh0VarT.k(this.i, 64, true);
    }
}
