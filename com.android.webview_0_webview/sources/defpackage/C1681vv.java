package defpackage;

/* JADX INFO: renamed from: vv, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1681vv extends AbstractC1651vJ {
    public static final PI[] d;
    public static final PI e;
    public long b;
    public long c;

    static {
        PI[] piArr = {new PI(24, 0)};
        d = piArr;
        e = piArr[0];
    }

    public C1681vv() {
        super(24, 0);
    }

    public static C1681vv c(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        ri.b();
        try {
            C1681vv c1681vv = new C1681vv(ri.c(d).b);
            c1681vv.b = ri.p(8);
            c1681vv.c = ri.p(16);
            return c1681vv;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(e);
        wiU.d(this.b, 8);
        wiU.d(this.c, 16);
    }

    public C1681vv(int i) {
        super(24, i);
    }
}
