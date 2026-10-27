package defpackage;

/* JADX INFO: compiled from: ItemDetails.java */
/* JADX INFO: loaded from: classes3.dex */
public final class a31 extends hv2 {
    public static final t50[] m;
    public static final t50 n;
    public String b;
    public String c;
    public String d;
    public ry1 e;
    public String f;
    public String g;
    public ry1 h;
    public String i;
    public int j;
    public int k;
    public qj3[] l;

    static {
        t50 t50Var = new t50(88, 1);
        m = new t50[]{new t50(72, 0), t50Var};
        n = t50Var;
    }

    public a31(int i) {
        super(88);
    }

    public static a31 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            int i = e70Var.c(m).b;
            a31 a31Var = new a31(i);
            a31Var.b = e70Var.w(8, false);
            a31Var.c = e70Var.w(16, false);
            a31Var.d = e70Var.w(24, false);
            a31Var.e = ry1.d(e70Var.t(32, false));
            a31Var.f = e70Var.w(40, true);
            a31Var.g = e70Var.w(48, true);
            a31Var.h = ry1.d(e70Var.t(56, true));
            a31Var.i = e70Var.w(64, true);
            if (i >= 1) {
                a31Var.j = e70Var.p(72);
                a31Var.k = e70Var.p(76);
                e70 e70VarT = e70Var.t(80, true);
                if (e70VarT == null) {
                    a31Var.l = null;
                } else {
                    int i2 = e70VarT.k(-1).b;
                    a31Var.l = new qj3[i2];
                    for (int i3 = 0; i3 < i2; i3++) {
                        a31Var.l[i3] = qj3.d(C0173a.g(i3, 8, 8, e70VarT, false));
                    }
                }
            }
            return a31Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(n);
        qh0VarT.m(this.b, 8, false);
        qh0VarT.m(this.c, 16, false);
        qh0VarT.m(this.d, 24, false);
        qh0VarT.k(this.e, 32, false);
        qh0VarT.m(this.f, 40, true);
        qh0VarT.m(this.g, 48, true);
        qh0VarT.k(this.h, 56, true);
        qh0VarT.m(this.i, 64, true);
        qh0VarT.b(this.j, 72);
        qh0VarT.b(this.k, 76);
        qj3[] qj3VarArr = this.l;
        if (qj3VarArr == null) {
            qh0VarT.q(80, true);
            return;
        }
        qh0 qh0VarR = qh0VarT.r(qj3VarArr.length, 80);
        int i = 0;
        while (true) {
            qj3[] qj3VarArr2 = this.l;
            if (i >= qj3VarArr2.length) {
                return;
            }
            qh0VarR.k(qj3VarArr2[i], (i * 8) + 8, false);
            i++;
        }
    }

    public a31() {
        this(1);
    }
}
