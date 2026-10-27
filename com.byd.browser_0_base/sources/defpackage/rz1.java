package defpackage;

/* JADX INFO: compiled from: PaymentRequestDetailsUpdate.java */
/* JADX INFO: loaded from: classes3.dex */
public final class rz1 extends hv2 {
    public static final t50 h = new t50(56, 0);
    public ry1 b;
    public wz1[] c;
    public cz1[] d;
    public String e;
    public String f;
    public d4 g;

    public rz1() {
        super(56);
        this.e = "";
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(h);
        qh0VarT.k(this.b, 8, true);
        wz1[] wz1VarArr = this.c;
        if (wz1VarArr != null) {
            qh0 qh0VarR = qh0VarT.r(wz1VarArr.length, 16);
            int i = 0;
            while (true) {
                wz1[] wz1VarArr2 = this.c;
                if (i >= wz1VarArr2.length) {
                    break;
                }
                qh0VarR.k(wz1VarArr2[i], (i * 8) + 8, false);
                i++;
            }
        } else {
            qh0VarT.q(16, true);
        }
        cz1[] cz1VarArr = this.d;
        if (cz1VarArr != null) {
            qh0 qh0VarR2 = qh0VarT.r(cz1VarArr.length, 24);
            int i2 = 0;
            while (true) {
                cz1[] cz1VarArr2 = this.d;
                if (i2 >= cz1VarArr2.length) {
                    break;
                }
                qh0VarR2.k(cz1VarArr2[i2], (i2 * 8) + 8, false);
                i2++;
            }
        } else {
            qh0VarT.q(24, true);
        }
        qh0VarT.m(this.e, 32, false);
        qh0VarT.m(this.f, 40, true);
        qh0VarT.k(this.g, 48, true);
    }
}
