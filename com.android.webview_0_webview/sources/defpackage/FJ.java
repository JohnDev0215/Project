package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class FJ extends AbstractC1651vJ {
    public static final PI[] c;
    public static final PI d;
    public EJ b;

    static {
        PI[] piArr = {new PI(24, 0)};
        c = piArr;
        d = piArr[0];
    }

    public FJ(int i) {
        super(24, i);
    }

    public static FJ c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        EJ ej = null;
        ri.b();
        try {
            FJ fj = new FJ(ri.c(c).b);
            PI piJ = ri.j(8);
            if (piJ.a != 0) {
                ej = new EJ();
                int i = piJ.b;
                if (i == 0) {
                    ej.b = BJ.c(ri.s(16, false));
                    ej.a = 0;
                } else if (i == 1) {
                    ej.c = C1816yJ.c(ri.s(16, false));
                    ej.a = 1;
                }
            }
            fj.b = ej;
            return fj;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        wi.u(d).j(this.b, 8, false);
    }
}
