package defpackage;

/* JADX INFO: renamed from: xJ, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1761xJ extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public long b;

    static {
        PI[] piArr = {new PI(16, 0)};
        c = piArr;
        d = piArr[0];
    }

    public C1761xJ(int i) {
        super(16, i);
    }

    public static C1761xJ c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            C1761xJ c1761xJ = new C1761xJ(ri.c(c).b);
            c1761xJ.b = ri.p(8);
            return c1761xJ;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        wi.u(d).d(this.b, 8);
    }
}
