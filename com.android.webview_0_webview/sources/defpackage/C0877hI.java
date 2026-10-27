package defpackage;

/* JADX INFO: renamed from: hI, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0877hI extends AbstractC1651vJ {
    public static final PI[] b;
    public static final PI c;

    static {
        PI[] piArr = {new PI(8, 0)};
        b = piArr;
        c = piArr[0];
    }

    public C0877hI() {
        super(8, 0);
    }

    public static C0877hI c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            return new C0877hI(ri.c(b).b);
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        wi.u(c);
    }

    public C0877hI(int i) {
        super(8, i);
    }
}
