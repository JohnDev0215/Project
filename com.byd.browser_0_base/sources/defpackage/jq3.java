package defpackage;

/* JADX INFO: compiled from: WebAuthnDomExceptionDetails.java */
/* JADX INFO: loaded from: classes.dex */
public final class jq3 extends hv2 {
    public static final t50[] d;
    public static final t50 e;
    public String b;
    public String c;

    static {
        t50 t50Var = new t50(24, 0);
        d = new t50[]{t50Var};
        e = t50Var;
    }

    public jq3(int i) {
        super(24);
    }

    public static jq3 d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            jq3 jq3Var = new jq3(e70Var.c(d).b);
            jq3Var.b = e70Var.w(8, false);
            jq3Var.c = e70Var.w(16, false);
            return jq3Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(e);
        qh0VarT.m(this.b, 8, false);
        qh0VarT.m(this.c, 16, false);
    }

    public jq3() {
        this(0);
    }
}
