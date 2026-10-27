package defpackage;

/* JADX INFO: renamed from: mF, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1153mF extends AbstractC1651vJ {
    public static final PI[] f;
    public static final PI g;
    public boolean b;
    public double c;
    public double d;
    public double e;

    static {
        PI[] piArr = {new PI(40, 0)};
        f = piArr;
        g = piArr[0];
    }

    public C1153mF(int i) {
        super(40, i);
        this.b = true;
        this.c = 0.0d;
        this.d = Double.POSITIVE_INFINITY;
        this.e = 1.0d;
    }

    public static C1153mF c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            C1153mF c1153mF = new C1153mF(ri.c(f).b);
            c1153mF.b = ri.d(8, 0);
            c1153mF.c = ri.k(16);
            c1153mF.d = ri.k(24);
            c1153mF.e = ri.k(32);
            return c1153mF;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(g);
        wiU.l(this.b, 8, 0);
        wiU.a(this.c, 16);
        wiU.a(this.d, 24);
        wiU.a(this.e, 32);
    }
}
