package defpackage;

/* JADX INFO: compiled from: UrlLoaderCompletionStatus.java */
/* JADX INFO: loaded from: classes3.dex */
public final class yj3 extends hv2 {
    public static final t50[] s;
    public static final t50 t;
    public int b;
    public int c;
    public boolean d;
    public boolean e;
    public ta3 f;
    public long g;
    public long h;
    public long i;
    public w00 j;
    public int k;
    public fr2 l;
    public gf m;
    public boolean n;
    public f72 o;
    public ob2 p;
    public boolean q;
    public boolean r;

    static {
        t50 t50Var = new t50(96, 0);
        s = new t50[]{t50Var};
        t = t50Var;
    }

    public yj3(int i) {
        super(96);
        this.b = 0;
        this.c = 0;
        this.d = false;
        this.e = false;
        this.g = 0L;
        this.h = 0L;
        this.i = 0L;
        this.k = 0;
        this.n = false;
        this.q = false;
        this.r = false;
    }

    public static yj3 d(e70 e70Var) {
        fr2 fr2Var;
        f72 f72Var;
        ob2 ob2Var = null;
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            yj3 yj3Var = new yj3(e70Var.c(s).b);
            yj3Var.b = e70Var.p(8);
            yj3Var.c = e70Var.p(12);
            yj3Var.d = e70Var.d(16, 0);
            yj3Var.e = e70Var.d(16, 1);
            yj3Var.n = e70Var.d(16, 2);
            yj3Var.q = e70Var.d(16, 3);
            yj3Var.r = e70Var.d(16, 4);
            int iP = e70Var.p(20);
            yj3Var.k = iP;
            if (!(iP >= 0 && iP <= 10)) {
                throw new q80("Invalid enum value.");
            }
            yj3Var.k = iP;
            yj3Var.f = ta3.d(e70Var.t(24, false));
            yj3Var.g = e70Var.s(32);
            yj3Var.h = e70Var.s(40);
            yj3Var.i = e70Var.s(48);
            yj3Var.j = w00.d(e70Var.t(56, true));
            e70 e70VarT = e70Var.t(64, true);
            if (e70VarT == null) {
                fr2Var = null;
            } else {
                e70VarT.b();
                try {
                    fr2Var = new fr2(e70VarT.c(fr2.b).b);
                } finally {
                }
            }
            yj3Var.l = fr2Var;
            yj3Var.m = gf.d(e70Var.t(72, true));
            e70VarT = e70Var.t(80, false);
            if (e70VarT == null) {
                f72Var = null;
            } else {
                e70VarT.b();
                try {
                    f72Var = new f72(e70VarT.c(f72.b).b);
                } finally {
                }
            }
            yj3Var.o = f72Var;
            e70VarT = e70Var.t(88, false);
            if (e70VarT != null) {
                e70VarT.b();
                try {
                    ob2 ob2Var2 = new ob2(e70VarT.c(ob2.d).b);
                    ob2Var2.b = e70VarT.p(8);
                    ob2Var2.c = e70VarT.d(12, 0);
                    e70VarT.a();
                    ob2Var = ob2Var2;
                } finally {
                }
            }
            yj3Var.p = ob2Var;
            return yj3Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(t);
        qh0VarT.b(this.b, 8);
        qh0VarT.b(this.c, 12);
        qh0VarT.d(16, 0, this.d);
        qh0VarT.d(16, 1, this.e);
        qh0VarT.d(16, 2, this.n);
        qh0VarT.d(16, 3, this.q);
        qh0VarT.d(16, 4, this.r);
        qh0VarT.b(this.k, 20);
        qh0VarT.k(this.f, 24, false);
        qh0VarT.f(32, this.g);
        qh0VarT.f(40, this.h);
        qh0VarT.f(48, this.i);
        qh0VarT.k(this.j, 56, true);
        qh0VarT.k(this.l, 64, true);
        qh0VarT.k(this.m, 72, true);
        qh0VarT.k(this.o, 80, false);
        qh0VarT.k(this.p, 88, false);
    }
}
