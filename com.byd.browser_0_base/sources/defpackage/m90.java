package defpackage;

/* JADX INFO: compiled from: DevicePublicKeyRequest.java */
/* JADX INFO: loaded from: classes.dex */
public final class m90 extends hv2 {
    public static final t50[] d;
    public static final t50 e;
    public int b;
    public String[] c;

    static {
        t50 t50Var = new t50(24, 0);
        d = new t50[]{t50Var};
        e = t50Var;
    }

    public m90(int i) {
        super(24);
    }

    public static m90 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            m90 m90Var = new m90(e70Var.c(d).b);
            int iP = e70Var.p(8);
            m90Var.b = iP;
            if (!(iP >= 0 && iP <= 3)) {
                throw new q80("Invalid enum value.");
            }
            m90Var.b = iP;
            e70 e70VarT = e70Var.t(16, false);
            int i = e70VarT.k(-1).b;
            m90Var.c = new String[i];
            for (int i2 = 0; i2 < i; i2++) {
                m90Var.c[i2] = C0175b.g(i2, 8, 8, e70VarT, false);
            }
            return m90Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(e);
        qh0VarT.b(this.b, 8);
        String[] strArr = this.c;
        if (strArr == null) {
            qh0VarT.q(16, false);
            return;
        }
        qh0 qh0VarR = qh0VarT.r(strArr.length, 16);
        int iF = 0;
        while (true) {
            String[] strArr2 = this.c;
            if (iF >= strArr2.length) {
                return;
            }
            iF = C0175b.f(iF, 8, 8, qh0VarR, strArr2[iF], false, iF, 1);
        }
    }
}
