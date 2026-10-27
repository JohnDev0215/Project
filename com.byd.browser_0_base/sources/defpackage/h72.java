package defpackage;

/* JADX INFO: compiled from: PublicKeyCredentialCreationOptions.java */
/* JADX INFO: loaded from: classes.dex */
public final class h72 extends hv2 {
    public static final t50[] w;
    public static final t50 x;
    public l72 b;
    public m72 c;
    public byte[] d;
    public j72[] e;
    public qa3 f;
    public i72[] g;
    public t9 h;
    public int i;
    public ok j;
    public boolean k;
    public boolean l;
    public int m;
    public boolean n;
    public String o;
    public boolean p;
    public int q;
    public boolean r;
    public byte[] s;
    public boolean t;
    public na2 u;
    public m90 v;

    static {
        t50 t50Var = new t50(120, 0);
        w = new t50[]{t50Var};
        x = t50Var;
    }

    public h72(int i) {
        super(120);
        this.t = false;
    }

    public static h72 d(e70 e70Var) {
        l72 l72Var;
        ok okVar = null;
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            h72 h72Var = new h72(e70Var.c(w).b);
            boolean z = false;
            e70 e70VarT = e70Var.t(8, false);
            if (e70VarT == null) {
                l72Var = null;
            } else {
                e70VarT.b();
                try {
                    l72Var = new l72(e70VarT.c(l72.d).b);
                    l72Var.b = e70VarT.w(8, false);
                    l72Var.c = e70VarT.w(16, false);
                } finally {
                }
            }
            h72Var.b = l72Var;
            h72Var.c = m72.d(e70Var.t(16, false));
            h72Var.d = e70Var.f(24, 0, -1);
            e70 e70VarT2 = e70Var.t(32, false);
            int i = e70VarT2.k(-1).b;
            h72Var.e = new j72[i];
            for (int i2 = 0; i2 < i; i2++) {
                h72Var.e[i2] = j72.d(C0173a.g(i2, 8, 8, e70VarT2, false));
            }
            h72Var.f = qa3.d(e70Var.t(40, true));
            e70 e70VarT3 = e70Var.t(48, false);
            int i3 = e70VarT3.k(-1).b;
            h72Var.g = new i72[i3];
            for (int i4 = 0; i4 < i3; i4++) {
                h72Var.g[i4] = i72.d(C0173a.g(i4, 8, 8, e70VarT3, false));
            }
            h72Var.h = t9.d(e70Var.t(56, true));
            int iP = e70Var.p(64);
            h72Var.i = iP;
            if (!(iP >= 0 && iP <= 3)) {
                throw new q80("Invalid enum value.");
            }
            h72Var.i = iP;
            h72Var.k = e70Var.d(68, 0);
            h72Var.l = e70Var.d(68, 1);
            h72Var.n = e70Var.d(68, 2);
            h72Var.p = e70Var.d(68, 3);
            h72Var.r = e70Var.d(68, 4);
            h72Var.t = e70Var.d(68, 5);
            e70VarT = e70Var.t(72, true);
            if (e70VarT != null) {
                e70VarT.b();
                try {
                    ok okVar2 = new ok(e70VarT.c(ok.d).b);
                    okVar2.b = e70VarT.f(8, 0, -1);
                    okVar2.c = e70VarT.f(16, 0, 65);
                    e70VarT.a();
                    okVar = okVar2;
                } finally {
                }
            }
            h72Var.j = okVar;
            int iP2 = e70Var.p(80);
            h72Var.m = iP2;
            if (!(iP2 >= 0 && iP2 <= 3)) {
                throw new q80("Invalid enum value.");
            }
            h72Var.m = iP2;
            int iP3 = e70Var.p(84);
            h72Var.q = iP3;
            if (iP3 >= 0 && iP3 <= 2) {
                z = true;
            }
            if (!z) {
                throw new q80("Invalid enum value.");
            }
            h72Var.q = iP3;
            h72Var.o = e70Var.w(88, true);
            h72Var.s = e70Var.f(96, 1, -1);
            h72Var.u = na2.d(e70Var.t(104, true));
            h72Var.v = m90.d(e70Var.t(112, true));
            return h72Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(x);
        qh0VarT.k(this.b, 8, false);
        qh0VarT.k(this.c, 16, false);
        qh0VarT.c(24, 0, -1, this.d);
        j72[] j72VarArr = this.e;
        if (j72VarArr != null) {
            qh0 qh0VarR = qh0VarT.r(j72VarArr.length, 32);
            int i = 0;
            while (true) {
                j72[] j72VarArr2 = this.e;
                if (i >= j72VarArr2.length) {
                    break;
                }
                qh0VarR.k(j72VarArr2[i], (i * 8) + 8, false);
                i++;
            }
        } else {
            qh0VarT.q(32, false);
        }
        qh0VarT.k(this.f, 40, true);
        i72[] i72VarArr = this.g;
        if (i72VarArr != null) {
            qh0 qh0VarR2 = qh0VarT.r(i72VarArr.length, 48);
            int i2 = 0;
            while (true) {
                i72[] i72VarArr2 = this.g;
                if (i2 >= i72VarArr2.length) {
                    break;
                }
                qh0VarR2.k(i72VarArr2[i2], (i2 * 8) + 8, false);
                i2++;
            }
        } else {
            qh0VarT.q(48, false);
        }
        qh0VarT.k(this.h, 56, true);
        qh0VarT.b(this.i, 64);
        qh0VarT.d(68, 0, this.k);
        qh0VarT.d(68, 1, this.l);
        qh0VarT.d(68, 2, this.n);
        qh0VarT.d(68, 3, this.p);
        qh0VarT.d(68, 4, this.r);
        qh0VarT.d(68, 5, this.t);
        qh0VarT.k(this.j, 72, true);
        qh0VarT.b(this.m, 80);
        qh0VarT.b(this.q, 84);
        qh0VarT.m(this.o, 88, true);
        qh0VarT.c(96, 1, -1, this.s);
        qh0VarT.k(this.u, 104, true);
        qh0VarT.k(this.v, 112, true);
    }
}
