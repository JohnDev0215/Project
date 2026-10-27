package defpackage;

/* JADX INFO: compiled from: CspSourceList.java */
/* JADX INFO: loaded from: classes3.dex */
public final class e20 extends hv2 {
    public static final t50[] p;
    public static final t50 q;
    public d20[] b;
    public String[] c;
    public c20[] d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;
    public boolean o;

    static {
        t50 t50Var = new t50(40, 0);
        p = new t50[]{t50Var};
        q = t50Var;
    }

    public e20(int i) {
        super(40);
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = false;
        this.j = false;
        this.k = false;
        this.l = false;
        this.m = false;
        this.n = false;
        this.o = false;
    }

    public static e20 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            e20 e20Var = new e20(e70Var.c(p).b);
            e70 e70VarT = e70Var.t(8, false);
            int i = e70VarT.k(-1).b;
            e20Var.b = new d20[i];
            for (int i2 = 0; i2 < i; i2++) {
                e20Var.b[i2] = d20.d(C0173a.g(i2, 8, 8, e70VarT, false));
            }
            e70 e70VarT2 = e70Var.t(16, false);
            int i3 = e70VarT2.k(-1).b;
            e20Var.c = new String[i3];
            for (int i4 = 0; i4 < i3; i4++) {
                e20Var.c[i4] = C0175b.g(i4, 8, 8, e70VarT2, false);
            }
            e70 e70VarT3 = e70Var.t(24, false);
            int i5 = e70VarT3.k(-1).b;
            e20Var.d = new c20[i5];
            for (int i6 = 0; i6 < i5; i6++) {
                e20Var.d[i6] = c20.d(C0173a.g(i6, 8, 8, e70VarT3, false));
            }
            e20Var.e = e70Var.d(32, 0);
            e20Var.f = e70Var.d(32, 1);
            e20Var.g = e70Var.d(32, 2);
            e20Var.h = e70Var.d(32, 3);
            e20Var.i = e70Var.d(32, 4);
            e20Var.j = e70Var.d(32, 5);
            e20Var.k = e70Var.d(32, 6);
            e20Var.l = e70Var.d(32, 7);
            e20Var.m = e70Var.d(33, 0);
            e20Var.n = e70Var.d(33, 1);
            e20Var.o = e70Var.d(33, 2);
            return e20Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(q);
        d20[] d20VarArr = this.b;
        if (d20VarArr != null) {
            qh0 qh0VarR = qh0VarT.r(d20VarArr.length, 8);
            int i = 0;
            while (true) {
                d20[] d20VarArr2 = this.b;
                if (i >= d20VarArr2.length) {
                    break;
                }
                qh0VarR.k(d20VarArr2[i], (i * 8) + 8, false);
                i++;
            }
        } else {
            qh0VarT.q(8, false);
        }
        String[] strArr = this.c;
        if (strArr != null) {
            qh0 qh0VarR2 = qh0VarT.r(strArr.length, 16);
            int iF = 0;
            while (true) {
                String[] strArr2 = this.c;
                if (iF >= strArr2.length) {
                    break;
                }
                iF = C0175b.f(iF, 8, 8, qh0VarR2, strArr2[iF], false, iF, 1);
            }
        } else {
            qh0VarT.q(16, false);
        }
        c20[] c20VarArr = this.d;
        if (c20VarArr != null) {
            qh0 qh0VarR3 = qh0VarT.r(c20VarArr.length, 24);
            int i2 = 0;
            while (true) {
                c20[] c20VarArr2 = this.d;
                if (i2 >= c20VarArr2.length) {
                    break;
                }
                qh0VarR3.k(c20VarArr2[i2], (i2 * 8) + 8, false);
                i2++;
            }
        } else {
            qh0VarT.q(24, false);
        }
        qh0VarT.d(32, 0, this.e);
        qh0VarT.d(32, 1, this.f);
        qh0VarT.d(32, 2, this.g);
        qh0VarT.d(32, 3, this.h);
        qh0VarT.d(32, 4, this.i);
        qh0VarT.d(32, 5, this.j);
        qh0VarT.d(32, 6, this.k);
        qh0VarT.d(32, 7, this.l);
        qh0VarT.d(33, 0, this.m);
        qh0VarT.d(33, 1, this.n);
        qh0VarT.d(33, 2, this.o);
    }

    public e20() {
        this(0);
    }
}
