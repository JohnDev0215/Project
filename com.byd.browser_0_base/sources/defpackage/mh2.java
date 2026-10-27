package defpackage;

/* JADX INFO: compiled from: SecurePaymentConfirmationRequest.java */
/* JADX INFO: loaded from: classes3.dex */
public final class mh2 extends hv2 {
    public static final t50[] j;
    public static final t50 k;
    public byte[][] b;
    public byte[] c;
    public qy1 d;
    public qa3 e;
    public et1 f;
    public String g;
    public String h;
    public boolean i;

    static {
        t50 t50Var = new t50(72, 0);
        j = new t50[]{t50Var};
        k = t50Var;
    }

    public mh2(int i) {
        super(72);
    }

    public static mh2 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            mh2 mh2Var = new mh2(e70Var.c(j).b);
            e70 e70VarT = e70Var.t(8, false);
            int i = e70VarT.k(-1).b;
            mh2Var.b = new byte[i][];
            for (int i2 = 0; i2 < i; i2++) {
                mh2Var.b[i2] = e70VarT.f((i2 * 8) + 8, 0, -1);
            }
            mh2Var.c = e70Var.f(16, 0, -1);
            mh2Var.d = qy1.d(e70Var.t(24, false));
            mh2Var.e = qa3.d(e70Var.t(32, true));
            mh2Var.f = et1.d(e70Var.t(40, true));
            mh2Var.g = e70Var.w(48, true);
            mh2Var.h = e70Var.w(56, false);
            mh2Var.i = e70Var.d(64, 0);
            return mh2Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(k);
        byte[][] bArr = this.b;
        if (bArr != null) {
            qh0 qh0VarR = qh0VarT.r(bArr.length, 8);
            int i = 0;
            while (true) {
                byte[][] bArr2 = this.b;
                if (i >= bArr2.length) {
                    break;
                }
                qh0VarR.c((i * 8) + 8, 0, -1, bArr2[i]);
                i++;
            }
        } else {
            qh0VarT.q(8, false);
        }
        qh0VarT.c(16, 0, -1, this.c);
        qh0VarT.k(this.d, 24, false);
        qh0VarT.k(this.e, 32, true);
        qh0VarT.k(this.f, 40, true);
        qh0VarT.m(this.g, 48, true);
        qh0VarT.m(this.h, 56, false);
        qh0VarT.d(64, 0, this.i);
    }
}
