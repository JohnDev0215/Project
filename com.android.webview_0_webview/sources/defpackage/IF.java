package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class IF extends AbstractC1651vJ {
    public static final PI[] b;
    public static final PI c;

    static {
        PI[] piArr = {new PI(8, 0)};
        b = piArr;
        c = piArr[0];
    }

    public IF() {
        super(8, 0);
    }

    public static IF c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            return new IF(ri.c(b).b);
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        wi.u(c);
    }

    public IF(int i) {
        super(8, i);
    }
}
