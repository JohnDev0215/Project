package defpackage;

/* JADX INFO: compiled from: PaymentMethodData.java */
/* JADX INFO: loaded from: classes3.dex */
public final class lz1 extends hv2 {
    public static final t50[] i;
    public static final t50 j;
    public String b;
    public String c;
    public int d;
    public int e;
    public int f;
    public int[] g;
    public mh2 h;

    static {
        t50 t50Var = new t50(56, 0);
        i = new t50[]{t50Var};
        j = t50Var;
    }

    public lz1(int i2) {
        super(56);
    }

    public static lz1 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            lz1 lz1Var = new lz1(e70Var.c(i).b);
            lz1Var.b = e70Var.w(8, false);
            lz1Var.c = e70Var.w(16, false);
            int iP = e70Var.p(24);
            lz1Var.d = iP;
            if (!(iP >= 0 && iP <= 1)) {
                throw new q80("Invalid enum value.");
            }
            lz1Var.d = iP;
            lz1Var.e = e70Var.p(28);
            lz1Var.f = e70Var.p(32);
            lz1Var.g = e70Var.r(40, 0);
            int i2 = 0;
            while (true) {
                int[] iArr = lz1Var.g;
                if (i2 >= iArr.length) {
                    lz1Var.h = mh2.d(e70Var.t(48, true));
                    return lz1Var;
                }
                int i3 = iArr[i2];
                if (!(i3 >= 0 && i3 <= 7)) {
                    throw new q80("Invalid enum value.");
                }
                i2++;
            }
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(j);
        qh0VarT.m(this.b, 8, false);
        qh0VarT.m(this.c, 16, false);
        qh0VarT.b(this.d, 24);
        qh0VarT.b(this.e, 28);
        qh0VarT.b(this.f, 32);
        qh0VarT.o(this.g, 40, 0);
        qh0VarT.k(this.h, 48, true);
    }

    public lz1() {
        this(0);
    }
}
