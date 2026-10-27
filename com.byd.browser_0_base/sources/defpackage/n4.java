package defpackage;

/* JADX INFO: compiled from: AllowCspFromHeaderValue.java */
/* JADX INFO: loaded from: classes3.dex */
public final class n4 extends ui3 {
    public boolean b;
    public et1 c;
    public String d;

    public static final n4 b(e70 e70Var) {
        t50 t50VarL = e70Var.l(16);
        if (t50VarL.a == 0) {
            return null;
        }
        n4 n4Var = new n4();
        int i = t50VarL.b;
        if (i == 0) {
            n4Var.b = e70Var.d(24, 0);
            n4Var.a = 0;
        } else if (i == 1) {
            n4Var.c = et1.d(e70Var.t(24, false));
            n4Var.a = 1;
        } else if (i == 2) {
            n4Var.d = e70Var.w(24, false);
            n4Var.a = 2;
        }
        return n4Var;
    }

    @Override // defpackage.ui3
    public final void a(qh0 qh0Var, int i) {
        qh0Var.b(16, i);
        qh0Var.b(this.a, i + 4);
        int i2 = this.a;
        if (i2 == 0) {
            qh0Var.d(i + 8, 0, this.b);
        } else if (i2 == 1) {
            qh0Var.k(this.c, i + 8, false);
        } else {
            if (i2 != 2) {
                return;
            }
            qh0Var.m(this.d, i + 8, false);
        }
    }
}
