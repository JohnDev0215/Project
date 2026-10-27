package defpackage;

/* JADX INFO: renamed from: gF, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0818gF extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public C1153mF b;

    static {
        PI[] piArr = {new PI(16, 0)};
        c = piArr;
        d = piArr[0];
    }

    public C0818gF() {
        super(16, 0);
    }

    public static C0818gF c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            C0818gF c0818gF = new C0818gF(ri.c(c).b);
            c0818gF.b = C1153mF.c(ri.s(8, false));
            return c0818gF;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        wi.u(d).i(this.b, 8, false);
    }

    public C0818gF(int i) {
        super(16, i);
    }
}
