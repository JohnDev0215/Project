package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class JL extends AbstractC1651vJ {
    public static final PI[] d;
    public static final PI e;
    public C0934iJ b;
    public C0490aM c;

    static {
        PI[] piArr = {new PI(24, 0)};
        d = piArr;
        e = piArr[0];
    }

    public JL() {
        super(24, 0);
    }

    public static JL c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            JL jl = new JL(ri.c(d).b);
            jl.b = ri.n(8, false);
            jl.c = C0490aM.c(ri.s(16, false));
            return jl;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(e);
        wiU.h(this.b, 8, false);
        wiU.i(this.c, 16, false);
    }

    public JL(int i) {
        super(24, i);
    }
}
