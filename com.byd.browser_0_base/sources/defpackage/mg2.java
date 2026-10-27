package defpackage;

/* JADX INFO: compiled from: SearchParamsVariance.java */
/* JADX INFO: loaded from: classes3.dex */
public final class mg2 extends ui3 {
    public String[] b;
    public String[] c;

    public static final mg2 b(e70 e70Var) {
        t50 t50VarL = e70Var.l(8);
        if (t50VarL.a == 0) {
            return null;
        }
        mg2 mg2Var = new mg2();
        int i = t50VarL.b;
        if (i == 0) {
            e70 e70VarT = e70Var.t(16, false);
            int i2 = e70VarT.k(-1).b;
            mg2Var.b = new String[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                mg2Var.b[i3] = C0175b.g(i3, 8, 8, e70VarT, false);
            }
            mg2Var.a = 0;
        } else if (i == 1) {
            e70 e70VarT2 = e70Var.t(16, false);
            int i4 = e70VarT2.k(-1).b;
            mg2Var.c = new String[i4];
            for (int i5 = 0; i5 < i4; i5++) {
                mg2Var.c[i5] = C0175b.g(i5, 8, 8, e70VarT2, false);
            }
            mg2Var.a = 1;
        }
        return mg2Var;
    }

    @Override // defpackage.ui3
    public final void a(qh0 qh0Var, int i) {
        qh0Var.b(16, i);
        qh0Var.b(this.a, i + 4);
        int i2 = this.a;
        if (i2 == 0) {
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
        } else {
            if (i2 != 1) {
                return;
            }
            String[] strArr3 = this.c;
            if (strArr3 == null) {
                qh0Var.q(i + 8, false);
                return;
            }
            qh0 qh0VarR2 = qh0Var.r(strArr3.length, i + 8);
            int iF2 = 0;
            while (true) {
                String[] strArr4 = this.c;
                if (iF2 >= strArr4.length) {
                    return;
                }
                iF2 = C0175b.f(iF2, 8, 8, qh0VarR2, strArr4[iF2], false, iF2, 1);
            }
        }
    }
}
