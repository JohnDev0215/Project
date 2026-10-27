package defpackage;

/* JADX INFO: compiled from: MakeCredentialAuthenticatorResponse.java */
/* JADX INFO: loaded from: classes.dex */
public final class ib1 extends hv2 {
    public static final t50[] t;
    public static final t50 u;
    public mv b;
    public int c;
    public byte[] d;
    public int[] e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    public byte[] l;
    public int m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public n90 s;

    static {
        t50 t50Var = new t50(64, 0);
        t = new t50[]{t50Var};
        u = t50Var;
    }

    public ib1(int i) {
        super(64);
    }

    public static ib1 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            ib1 ib1Var = new ib1(e70Var.c(t).b);
            ib1Var.b = mv.d(e70Var.t(8, false));
            int iP = e70Var.p(16);
            ib1Var.c = iP;
            n3.t1(iP);
            ib1Var.c = ib1Var.c;
            ib1Var.f = e70Var.d(20, 0);
            ib1Var.g = e70Var.d(20, 1);
            ib1Var.h = e70Var.d(20, 2);
            ib1Var.i = e70Var.d(20, 3);
            ib1Var.j = e70Var.d(20, 4);
            ib1Var.k = e70Var.d(20, 5);
            ib1Var.n = e70Var.d(20, 6);
            ib1Var.o = e70Var.d(20, 7);
            ib1Var.p = e70Var.d(21, 0);
            ib1Var.q = e70Var.d(21, 1);
            ib1Var.r = e70Var.d(21, 2);
            ib1Var.d = e70Var.f(24, 0, -1);
            ib1Var.e = e70Var.r(32, 0);
            int i = 0;
            while (true) {
                int[] iArr = ib1Var.e;
                if (i >= iArr.length) {
                    ib1Var.l = e70Var.f(40, 1, -1);
                    ib1Var.m = e70Var.p(48);
                    ib1Var.s = n90.d(e70Var.t(56, true));
                    return ib1Var;
                }
                int i2 = iArr[i];
                if (!(i2 >= 0 && i2 <= 4)) {
                    throw new q80("Invalid enum value.");
                }
                i++;
            }
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(u);
        qh0VarT.k(this.b, 8, false);
        qh0VarT.b(this.c, 16);
        qh0VarT.d(20, 0, this.f);
        qh0VarT.d(20, 1, this.g);
        qh0VarT.d(20, 2, this.h);
        qh0VarT.d(20, 3, this.i);
        qh0VarT.d(20, 4, this.j);
        qh0VarT.d(20, 5, this.k);
        qh0VarT.d(20, 6, this.n);
        qh0VarT.d(20, 7, this.o);
        qh0VarT.d(21, 0, this.p);
        qh0VarT.d(21, 1, this.q);
        qh0VarT.d(21, 2, this.r);
        qh0VarT.c(24, 0, -1, this.d);
        qh0VarT.o(this.e, 32, 0);
        qh0VarT.c(40, 1, -1, this.l);
        qh0VarT.b(this.m, 48);
        qh0VarT.k(this.s, 56, true);
    }

    public ib1() {
        this(0);
    }
}
