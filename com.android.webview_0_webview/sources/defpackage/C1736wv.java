package defpackage;

/* JADX INFO: renamed from: wv, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1736wv extends AbstractC1651vJ {
    public static final PI[] d;
    public static final PI e;
    public int b;
    public long c;

    static {
        PI[] piArr = {new PI(24, 0)};
        d = piArr;
        e = piArr[0];
    }

    public C1736wv() {
        super(24, 0);
    }

    public static C1736wv c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            C1736wv c1736wv = new C1736wv(ri.c(d).b);
            c1736wv.b = ri.m(8);
            c1736wv.c = ri.p(16);
            return c1736wv;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(e);
        wiU.c(this.b, 8);
        wiU.d(this.c, 16);
    }

    public C1736wv(int i) {
        super(24, i);
    }
}
