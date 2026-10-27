package defpackage;

/* JADX INFO: renamed from: oK, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1268oK extends AbstractC1651vJ {
    public static final PI[] d;
    public static final PI e;
    public InterfaceC0488aK b;
    public int c;

    static {
        PI[] piArr = {new PI(16, 0)};
        d = piArr;
        e = piArr[0];
    }

    public C1268oK() {
        super(16, 0);
        this.b = PJ.s;
    }

    public static C1268oK c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            C1268oK c1268oK = new C1268oK(ri.c(d).b);
            c1268oK.b = ri.v(8, false).J();
            c1268oK.c = ri.m(12);
            return c1268oK;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(e);
        wiU.k(this.b, 8, false);
        wiU.c(this.c, 12);
    }

    public C1268oK(int i) {
        super(16, i);
        this.b = PJ.s;
    }
}
