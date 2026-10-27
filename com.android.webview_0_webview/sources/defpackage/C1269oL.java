package defpackage;

/* JADX INFO: renamed from: oL, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1269oL extends AbstractC1651vJ {
    public static final PI[] d;
    public static final PI e;
    public String b;
    public VJ c;

    static {
        PI[] piArr = {new PI(24, 0)};
        d = piArr;
        e = piArr[0];
    }

    public C1269oL() {
        super(24, 0);
        this.c = PJ.s;
    }

    public static C1269oL c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            C1269oL c1269oL = new C1269oL(ri.c(d).b);
            c1269oL.b = ri.u(8, false);
            c1269oL.c = ri.q(16, false);
            return c1269oL;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(e);
        wiU.e(this.b, 8, false);
        wiU.k(this.c, 16, false);
    }

    public C1269oL(int i) {
        super(24, i);
        this.c = PJ.s;
    }
}
