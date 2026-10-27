package defpackage;

/* JADX INFO: renamed from: jw, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1021jw extends AbstractC1651vJ {
    public static final PI[] d;
    public static final PI e;
    public boolean b;
    public boolean c;

    static {
        PI[] piArr = {new PI(16, 0)};
        d = piArr;
        e = piArr[0];
    }

    public C1021jw(int i) {
        super(16, i);
    }

    public static C1021jw c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            C1021jw c1021jw = new C1021jw(ri.c(d).b);
            c1021jw.b = ri.d(8, 0);
            c1021jw.c = ri.d(8, 1);
            return c1021jw;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(e);
        wiU.l(this.b, 8, 0);
        wiU.l(this.c, 8, 1);
    }
}
