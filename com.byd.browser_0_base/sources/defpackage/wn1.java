package defpackage;

/* JADX INFO: compiled from: NoVarySearchWithParseError.java */
/* JADX INFO: loaded from: classes3.dex */
public final class wn1 extends ui3 {
    public vn1 b;
    public int c;

    public static final wn1 b(e70 e70Var) {
        t50 t50VarL = e70Var.l(128);
        vn1 vn1Var = null;
        if (t50VarL.a == 0) {
            return null;
        }
        wn1 wn1Var = new wn1();
        boolean z = false;
        int i = t50VarL.b;
        if (i == 0) {
            e70 e70VarT = e70Var.t(136, false);
            if (e70VarT != null) {
                e70VarT.b();
                try {
                    vn1Var = new vn1(e70VarT.c(vn1.d).b);
                    vn1Var.b = mg2.b(e70VarT);
                    vn1Var.c = e70VarT.d(24, 0);
                } finally {
                    e70VarT.a();
                }
            }
            wn1Var.b = vn1Var;
            wn1Var.a = 0;
        } else if (i == 1) {
            int iP = e70Var.p(136);
            wn1Var.c = iP;
            if (iP >= 0 && iP <= 7) {
                z = true;
            }
            if (!z) {
                throw new q80("Invalid enum value.");
            }
            wn1Var.c = iP;
            wn1Var.a = 1;
        }
        return wn1Var;
    }

    @Override // defpackage.ui3
    public final void a(qh0 qh0Var, int i) {
        qh0Var.b(16, i);
        qh0Var.b(this.a, i + 4);
        int i2 = this.a;
        if (i2 == 0) {
            qh0Var.k(this.b, i + 8, false);
        } else {
            if (i2 != 1) {
                return;
            }
            qh0Var.b(this.c, i + 8);
        }
    }
}
