package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class RG extends AbstractC1651vJ {
    public static final PI[] d;
    public static final PI e;
    public float b;
    public float c;

    static {
        PI[] piArr = {new PI(16, 0)};
        d = piArr;
        e = piArr[0];
    }

    public RG() {
        super(16, 0);
    }

    public static RG c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            RG rg = new RG(ri.c(d).b);
            rg.b = ri.l(8);
            rg.c = ri.l(12);
            return rg;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(e);
        wiU.b(this.b, 8);
        wiU.b(this.c, 12);
    }

    public RG(int i) {
        super(16, i);
    }
}
