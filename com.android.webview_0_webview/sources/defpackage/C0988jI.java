package defpackage;

/* JADX INFO: renamed from: jI, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0988jI extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public long b;

    static {
        PI[] piArr = {new PI(16, 0)};
        c = piArr;
        d = piArr[0];
    }

    public C0988jI() {
        super(16, 0);
    }

    public static C0988jI c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            C0988jI c0988jI = new C0988jI(ri.c(c).b);
            c0988jI.b = ri.p(8);
            return c0988jI;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        wi.u(d).d(this.b, 8);
    }

    public C0988jI(int i) {
        super(16, i);
    }
}
