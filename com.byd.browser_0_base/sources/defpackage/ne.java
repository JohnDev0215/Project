package defpackage;

/* JADX INFO: compiled from: BigBuffer.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ne extends ui3 {
    public byte[] b;
    public oe c;
    public boolean d;

    public static final ne b(e70 e70Var, int i) {
        t50 t50VarL = e70Var.l(i);
        oe oeVar = null;
        if (t50VarL.a == 0) {
            return null;
        }
        ne neVar = new ne();
        int i2 = t50VarL.b;
        if (i2 == 0) {
            neVar.b = e70Var.f(i + 8, 0, -1);
            neVar.a = 0;
        } else if (i2 == 1) {
            e70 e70VarT = e70Var.t(i + 8, false);
            if (e70VarT != null) {
                e70VarT.b();
                try {
                    oeVar = new oe(e70VarT.c(oe.d).b);
                    oeVar.b = e70VarT.x(8, false).A();
                    oeVar.c = e70VarT.p(12);
                } finally {
                    e70VarT.a();
                }
            }
            neVar.c = oeVar;
            neVar.a = 1;
        } else if (i2 == 2) {
            neVar.d = e70Var.d(i + 8, 0);
            neVar.a = 2;
        }
        return neVar;
    }

    @Override // defpackage.ui3
    public final void a(qh0 qh0Var, int i) {
        qh0Var.b(16, i);
        qh0Var.b(this.a, i + 4);
        int i2 = this.a;
        if (i2 == 0) {
            qh0Var.c(i + 8, 0, -1, this.b);
        } else if (i2 == 1) {
            qh0Var.k(this.c, i + 8, false);
        } else {
            if (i2 != 2) {
                return;
            }
            qh0Var.d(i + 8, 0, this.d);
        }
    }
}
