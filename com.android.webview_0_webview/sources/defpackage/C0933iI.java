package defpackage;

/* JADX INFO: renamed from: iI, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0933iI extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public boolean b;

    static {
        PI[] piArr = {new PI(16, 0)};
        c = piArr;
        d = piArr[0];
    }

    public C0933iI() {
        super(16, 0);
    }

    public static C0933iI c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            C0933iI c0933iI = new C0933iI(ri.c(c).b);
            c0933iI.b = ri.d(8, 0);
            return c0933iI;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        wi.u(d).l(this.b, 8, 0);
    }

    public C0933iI(int i) {
        super(16, i);
    }
}
