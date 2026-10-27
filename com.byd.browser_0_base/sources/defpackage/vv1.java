package defpackage;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: ParsedHeaders.java */
/* JADX INFO: loaded from: classes3.dex */
public final class vv1 extends hv2 {
    public static final t50[] q;
    public static final t50 r;
    public uy[] b;
    public n4 c;
    public a20 d;
    public b20 e;
    public int f;
    public int[] g;
    public int[] h;
    public int i;
    public r71[] j;
    public wa3 k;
    public int[] l;
    public HashMap m;
    public xk3[] n;
    public String[] o;
    public wn1 p;

    static {
        t50 t50Var = new t50(144, 0);
        q = new t50[]{t50Var};
        r = t50Var;
    }

    public vv1(int i) {
        super(144);
        this.i = 0;
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(r);
        uy[] uyVarArr = this.b;
        if (uyVarArr != null) {
            qh0 qh0VarR = qh0VarT.r(uyVarArr.length, 8);
            int i = 0;
            while (true) {
                uy[] uyVarArr2 = this.b;
                if (i >= uyVarArr2.length) {
                    break;
                }
                qh0VarR.k(uyVarArr2[i], (i * 8) + 8, false);
                i++;
            }
        } else {
            qh0VarT.q(8, false);
        }
        qh0VarT.l(this.c, 16, true);
        qh0VarT.k(this.d, 32, false);
        qh0VarT.k(this.e, 40, false);
        qh0VarT.b(this.f, 48);
        qh0VarT.b(this.i, 52);
        qh0VarT.o(this.g, 56, 1);
        qh0VarT.o(this.h, 64, 1);
        r71[] r71VarArr = this.j;
        if (r71VarArr != null) {
            qh0 qh0VarR2 = qh0VarT.r(r71VarArr.length, 72);
            int i2 = 0;
            while (true) {
                r71[] r71VarArr2 = this.j;
                if (i2 >= r71VarArr2.length) {
                    break;
                }
                qh0VarR2.k(r71VarArr2[i2], (i2 * 8) + 8, false);
                i2++;
            }
        } else {
            qh0VarT.q(72, false);
        }
        qh0VarT.l(this.k, 80, true);
        qh0VarT.o(this.l, 96, 0);
        if (this.m == null) {
            qh0VarT.q(104, true);
        } else {
            qh0VarT.f(104, ((long) qh0VarT.b.d) - ((long) (qh0VarT.a + 104)));
            qh0 qh0VarT2 = qh0VarT.t(n3.f);
            int size = this.m.size();
            String[] strArr = new String[size];
            String[] strArr2 = new String[size];
            int i3 = 0;
            for (Map.Entry entry : this.m.entrySet()) {
                strArr[i3] = (String) entry.getKey();
                strArr2[i3] = (String) entry.getValue();
                i3++;
            }
            qh0 qh0VarR3 = qh0VarT2.r(size, 8);
            int iF = 0;
            while (iF < size) {
                iF = C0175b.f(iF, 8, 8, qh0VarR3, strArr[iF], false, iF, 1);
                strArr = strArr;
                strArr2 = strArr2;
            }
            String[] strArr3 = strArr2;
            qh0 qh0VarR4 = qh0VarT2.r(size, 16);
            for (int iF2 = 0; iF2 < size; iF2 = C0175b.f(iF2, 8, 8, qh0VarR4, strArr3[iF2], false, iF2, 1)) {
            }
        }
        xk3[] xk3VarArr = this.n;
        if (xk3VarArr != null) {
            qh0 qh0VarR5 = qh0VarT.r(xk3VarArr.length, 112);
            int i4 = 0;
            while (true) {
                xk3[] xk3VarArr2 = this.n;
                if (i4 >= xk3VarArr2.length) {
                    break;
                }
                qh0VarR5.k(xk3VarArr2[i4], (i4 * 8) + 8, false);
                i4++;
            }
        } else {
            qh0VarT.q(112, true);
        }
        String[] strArr4 = this.o;
        if (strArr4 != null) {
            qh0 qh0VarR6 = qh0VarT.r(strArr4.length, 120);
            int iF3 = 0;
            while (true) {
                String[] strArr5 = this.o;
                if (iF3 >= strArr5.length) {
                    break;
                }
                iF3 = C0175b.f(iF3, 8, 8, qh0VarR6, strArr5[iF3], false, iF3, 1);
            }
        } else {
            qh0VarT.q(120, true);
        }
        qh0VarT.l(this.p, 128, true);
    }
}
