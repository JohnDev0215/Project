package defpackage;

/* JADX INFO: compiled from: Entity.java */
/* JADX INFO: loaded from: classes3.dex */
public final class uh0 extends hv2 {
    public static final t50[] d;
    public static final t50 e;
    public String b;
    public u62[] c;

    static {
        t50 t50Var = new t50(24, 0);
        d = new t50[]{t50Var};
        e = t50Var;
    }

    public uh0(int i) {
        super(24);
    }

    public static uh0 d(e70 e70Var) {
        u62 u62Var;
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            uh0 uh0Var = new uh0(e70Var.c(d).b);
            uh0Var.b = e70Var.w(8, false);
            e70 e70VarT = e70Var.t(16, false);
            int i = e70VarT.k(-1).b;
            uh0Var.c = new u62[i];
            for (int i2 = 0; i2 < i; i2++) {
                e70 e70VarG = C0173a.g(i2, 8, 8, e70VarT, false);
                u62[] u62VarArr = uh0Var.c;
                if (e70VarG == null) {
                    u62Var = null;
                } else {
                    e70VarG.b();
                    try {
                        u62Var = new u62(e70VarG.c(u62.d).b);
                        u62Var.b = e70VarG.w(8, false);
                        u62Var.c = vk3.b(e70VarG);
                    } finally {
                    }
                }
                u62VarArr[i2] = u62Var;
            }
            return uh0Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(e);
        qh0VarT.m(this.b, 8, false);
        u62[] u62VarArr = this.c;
        if (u62VarArr == null) {
            qh0VarT.q(16, false);
            return;
        }
        qh0 qh0VarR = qh0VarT.r(u62VarArr.length, 16);
        int i = 0;
        while (true) {
            u62[] u62VarArr2 = this.c;
            if (i >= u62VarArr2.length) {
                return;
            }
            qh0VarR.k(u62VarArr2[i], (i * 8) + 8, false);
            i++;
        }
    }
}
