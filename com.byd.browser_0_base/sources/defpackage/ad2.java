package defpackage;

/* JADX INFO: compiled from: RunOrClosePipeInput.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ad2 extends ui3 {
    public kb2 b;
    public ph0 c;
    public fg1 d;
    public up1 e;

    public static final ad2 b(e70 e70Var) {
        t50 t50VarL = e70Var.l(8);
        kb2 kb2Var = null;
        up1 up1Var = null;
        fg1 fg1Var = null;
        ph0 ph0Var = null;
        if (t50VarL.a == 0) {
            return null;
        }
        ad2 ad2Var = new ad2();
        int i = t50VarL.b;
        if (i == 0) {
            e70 e70VarT = e70Var.t(16, false);
            if (e70VarT != null) {
                e70VarT.b();
                try {
                    kb2Var = new kb2(e70VarT.c(kb2.c).b);
                    kb2Var.b = e70VarT.p(8);
                } finally {
                }
            }
            ad2Var.b = kb2Var;
            ad2Var.a = 0;
        } else if (i == 1) {
            e70 e70VarT2 = e70Var.t(16, false);
            if (e70VarT2 != null) {
                e70VarT2.b();
                try {
                    ph0Var = new ph0(e70VarT2.c(ph0.c).b);
                    ph0Var.b = e70VarT2.s(8);
                } finally {
                }
            }
            ad2Var.c = ph0Var;
            ad2Var.a = 1;
        } else if (i == 2) {
            e70 e70VarT3 = e70Var.t(16, false);
            if (e70VarT3 != null) {
                e70VarT3.b();
                try {
                    fg1Var = new fg1(e70VarT3.c(fg1.b).b);
                } finally {
                }
            }
            ad2Var.d = fg1Var;
            ad2Var.a = 2;
        } else if (i == 3) {
            e70 e70VarT4 = e70Var.t(16, false);
            if (e70VarT4 != null) {
                e70VarT4.b();
                try {
                    up1Var = new up1(e70VarT4.c(up1.b).b);
                } finally {
                }
            }
            ad2Var.e = up1Var;
            ad2Var.a = 3;
        }
        return ad2Var;
    }

    @Override // defpackage.ui3
    public final void a(qh0 qh0Var, int i) {
        qh0Var.b(16, i);
        qh0Var.b(this.a, i + 4);
        int i2 = this.a;
        if (i2 == 0) {
            qh0Var.k(this.b, i + 8, false);
            return;
        }
        if (i2 == 1) {
            qh0Var.k(this.c, i + 8, false);
        } else if (i2 == 2) {
            qh0Var.k(this.d, i + 8, false);
        } else {
            if (i2 != 3) {
                return;
            }
            qh0Var.k(this.e, i + 8, false);
        }
    }
}
