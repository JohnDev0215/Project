package defpackage;

/* JADX INFO: compiled from: TimingAllowOrigin.java */
/* JADX INFO: loaded from: classes3.dex */
public final class wa3 extends ui3 {
    public String[] b;
    public byte c;

    public static final wa3 b(e70 e70Var) {
        t50 t50VarL = e70Var.l(80);
        if (t50VarL.a == 0) {
            return null;
        }
        wa3 wa3Var = new wa3();
        int i = t50VarL.b;
        if (i == 0) {
            e70 e70VarT = e70Var.t(88, false);
            int i2 = e70VarT.k(-1).b;
            wa3Var.b = new String[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                wa3Var.b[i3] = C0175b.g(i3, 8, 8, e70VarT, false);
            }
            wa3Var.a = 0;
        } else if (i == 1) {
            wa3Var.c = e70Var.e(88);
            wa3Var.a = 1;
        }
        return wa3Var;
    }

    @Override // defpackage.ui3
    public final void a(qh0 qh0Var, int i) {
        qh0Var.b(16, i);
        qh0Var.b(this.a, i + 4);
        int i2 = this.a;
        if (i2 != 0) {
            if (i2 != 1) {
                return;
            }
            qh0Var.b.b.put(qh0Var.a + i + 8, this.c);
            return;
        }
        String[] strArr = this.b;
        if (strArr == null) {
            qh0Var.q(i + 8, false);
            return;
        }
        qh0 qh0VarR = qh0Var.r(strArr.length, i + 8);
        int iF = 0;
        while (true) {
            String[] strArr2 = this.b;
            if (iF >= strArr2.length) {
                return;
            }
            iF = C0175b.f(iF, 8, 8, qh0VarR, strArr2[iF], false, iF, 1);
        }
    }
}
