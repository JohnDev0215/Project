package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class WF extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public int b;

    static {
        PI[] piArr = {new PI(16, 0)};
        c = piArr;
        d = piArr[0];
    }

    public WF() {
        super(16, 0);
    }

    public static WF c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            WF wf = new WF(ri.c(c).b);
            wf.b = ri.m(8);
            return wf;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        wi.u(d).c(this.b, 8);
    }

    public WF(int i) {
        super(16, i);
    }
}
