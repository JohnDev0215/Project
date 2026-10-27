package defpackage;

/* JADX INFO: renamed from: fG, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0763fG extends AbstractC1651vJ {
    public static final PI[] d;
    public static final PI e;
    public C1373qF b;
    public int c;

    static {
        PI[] piArr = {new PI(24, 0)};
        d = piArr;
        e = piArr[0];
    }

    public C0763fG() {
        super(24, 0);
    }

    public static C0763fG c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            C0763fG c0763fG = new C0763fG(ri.c(d).b);
            c0763fG.b = C1373qF.c(ri.s(8, false));
            c0763fG.c = ri.m(16);
            return c0763fG;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(e);
        wiU.i(this.b, 8, false);
        wiU.c(this.c, 16);
    }

    public C0763fG(int i) {
        super(24, i);
    }
}
