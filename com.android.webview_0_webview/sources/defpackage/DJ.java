package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class DJ extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public int b;

    static {
        PI[] piArr = {new PI(16, 0)};
        c = piArr;
        d = piArr[0];
    }

    public DJ(int i) {
        super(16, i);
    }

    public static DJ c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            DJ dj = new DJ(ri.c(c).b);
            dj.b = ri.m(8);
            return dj;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        wi.u(d).c(this.b, 8);
    }
}
