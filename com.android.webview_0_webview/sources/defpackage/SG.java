package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class SG extends AbstractC1651vJ {
    public static final PI[] f;
    public static final PI g;
    public float b;
    public float c;
    public float d;
    public float e;

    static {
        PI[] piArr = {new PI(24, 0)};
        f = piArr;
        g = piArr[0];
    }

    public SG() {
        super(24, 0);
    }

    public static SG c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            SG sg = new SG(ri.c(f).b);
            sg.b = ri.l(8);
            sg.c = ri.l(12);
            sg.d = ri.l(16);
            sg.e = ri.l(20);
            return sg;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(g);
        wiU.b(this.b, 8);
        wiU.b(this.c, 12);
        wiU.b(this.d, 16);
        wiU.b(this.e, 20);
    }

    public SG(int i) {
        super(24, i);
    }
}
