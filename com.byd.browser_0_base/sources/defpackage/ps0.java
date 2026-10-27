package defpackage;

/* JADX INFO: compiled from: GetAssertionAuthenticatorResponse.java */
/* JADX INFO: loaded from: classes.dex */
public final class ps0 extends hv2 {
    public static final t50[] s;
    public static final t50 t;
    public mv b;
    public int c;
    public byte[] d;
    public byte[] e;
    public boolean f;
    public boolean g;
    public boolean h;
    public uk3[] i;
    public boolean j;
    public s32 k;
    public boolean l;
    public boolean m;
    public byte[] n;
    public boolean o;
    public boolean p;
    public byte[] q;
    public n90 r;

    static {
        t50 t50Var = new t50(80, 0);
        s = new t50[]{t50Var};
        t = t50Var;
    }

    public ps0(int i) {
        super(80);
    }

    public static ps0 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            ps0 ps0Var = new ps0(e70Var.c(s).b);
            ps0Var.b = mv.d(e70Var.t(8, false));
            int iP = e70Var.p(16);
            ps0Var.c = iP;
            n3.t1(iP);
            ps0Var.c = ps0Var.c;
            ps0Var.f = e70Var.d(20, 0);
            ps0Var.g = e70Var.d(20, 1);
            ps0Var.h = e70Var.d(20, 2);
            ps0Var.j = e70Var.d(20, 3);
            ps0Var.l = e70Var.d(20, 4);
            ps0Var.m = e70Var.d(20, 5);
            ps0Var.o = e70Var.d(20, 6);
            ps0Var.p = e70Var.d(20, 7);
            ps0Var.d = e70Var.f(24, 0, -1);
            ps0Var.e = e70Var.f(32, 1, -1);
            e70 e70VarT = e70Var.t(40, true);
            if (e70VarT == null) {
                ps0Var.i = null;
            } else {
                int i = e70VarT.k(-1).b;
                ps0Var.i = new uk3[i];
                for (int i2 = 0; i2 < i; i2++) {
                    ps0Var.i[i2] = uk3.d(C0173a.g(i2, 8, 8, e70VarT, false));
                }
            }
            ps0Var.k = s32.d(e70Var.t(48, true));
            ps0Var.n = e70Var.f(56, 1, -1);
            ps0Var.q = e70Var.f(64, 1, -1);
            ps0Var.r = n90.d(e70Var.t(72, true));
            return ps0Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(t);
        qh0VarT.k(this.b, 8, false);
        qh0VarT.b(this.c, 16);
        qh0VarT.d(20, 0, this.f);
        qh0VarT.d(20, 1, this.g);
        qh0VarT.d(20, 2, this.h);
        qh0VarT.d(20, 3, this.j);
        qh0VarT.d(20, 4, this.l);
        qh0VarT.d(20, 5, this.m);
        qh0VarT.d(20, 6, this.o);
        qh0VarT.d(20, 7, this.p);
        qh0VarT.c(24, 0, -1, this.d);
        qh0VarT.c(32, 1, -1, this.e);
        uk3[] uk3VarArr = this.i;
        if (uk3VarArr != null) {
            qh0 qh0VarR = qh0VarT.r(uk3VarArr.length, 40);
            int i = 0;
            while (true) {
                uk3[] uk3VarArr2 = this.i;
                if (i >= uk3VarArr2.length) {
                    break;
                }
                qh0VarR.k(uk3VarArr2[i], (i * 8) + 8, false);
                i++;
            }
        } else {
            qh0VarT.q(40, true);
        }
        qh0VarT.k(this.k, 48, true);
        qh0VarT.c(56, 1, -1, this.n);
        qh0VarT.c(64, 1, -1, this.q);
        qh0VarT.k(this.r, 72, true);
    }

    public ps0() {
        this(0);
    }
}
