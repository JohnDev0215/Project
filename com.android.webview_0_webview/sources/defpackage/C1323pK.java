package defpackage;

/* JADX INFO: renamed from: pK, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1323pK extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public long b;

    static {
        PI[] piArr = {new PI(16, 0)};
        c = piArr;
        d = piArr[0];
    }

    public C1323pK(int i) {
        super(16, i);
    }

    public static C1323pK c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            C1323pK c1323pK = new C1323pK(ri.c(c).b);
            c1323pK.b = ri.p(8);
            return c1323pK;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        wi.u(d).d(this.b, 8);
    }
}
