package defpackage;

/* JADX INFO: compiled from: PaymentAddress.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ky1 extends hv2 {
    public static final t50[] l;
    public static final t50 m;
    public String b;
    public String[] c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;

    static {
        t50 t50Var = new t50(88, 0);
        l = new t50[]{t50Var};
        m = t50Var;
    }

    public ky1(int i) {
        super(88);
    }

    public static ky1 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            ky1 ky1Var = new ky1(e70Var.c(l).b);
            ky1Var.b = e70Var.w(8, false);
            e70 e70VarT = e70Var.t(16, false);
            int i = e70VarT.k(-1).b;
            ky1Var.c = new String[i];
            for (int i2 = 0; i2 < i; i2++) {
                ky1Var.c[i2] = C0175b.g(i2, 8, 8, e70VarT, false);
            }
            ky1Var.d = e70Var.w(24, false);
            ky1Var.e = e70Var.w(32, false);
            ky1Var.f = e70Var.w(40, false);
            ky1Var.g = e70Var.w(48, false);
            ky1Var.h = e70Var.w(56, false);
            ky1Var.i = e70Var.w(64, false);
            ky1Var.j = e70Var.w(72, false);
            ky1Var.k = e70Var.w(80, false);
            return ky1Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(m);
        qh0VarT.m(this.b, 8, false);
        String[] strArr = this.c;
        if (strArr != null) {
            qh0 qh0VarR = qh0VarT.r(strArr.length, 16);
            int iF = 0;
            while (true) {
                String[] strArr2 = this.c;
                if (iF >= strArr2.length) {
                    break;
                }
                iF = C0175b.f(iF, 8, 8, qh0VarR, strArr2[iF], false, iF, 1);
            }
        } else {
            qh0VarT.q(16, false);
        }
        qh0VarT.m(this.d, 24, false);
        qh0VarT.m(this.e, 32, false);
        qh0VarT.m(this.f, 40, false);
        qh0VarT.m(this.g, 48, false);
        qh0VarT.m(this.h, 56, false);
        qh0VarT.m(this.i, 64, false);
        qh0VarT.m(this.j, 72, false);
        qh0VarT.m(this.k, 80, false);
    }

    public ky1() {
        this(0);
    }
}
