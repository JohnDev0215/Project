package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class Iv extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public String b;

    static {
        PI[] piArr = {new PI(16, 0)};
        c = piArr;
        d = piArr[0];
    }

    public Iv() {
        super(16, 0);
    }

    public static Iv c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            Iv iv = new Iv(ri.c(c).b);
            iv.b = ri.u(8, false);
            return iv;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        wi.u(d).e(this.b, 8, false);
    }

    public Iv(int i) {
        super(16, i);
    }
}
