package defpackage;

/* JADX INFO: renamed from: zJ, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1871zJ extends AbstractC1651vJ {
    public static final PI[] b;
    public static final PI c;

    static {
        PI[] piArr = {new PI(8, 0)};
        b = piArr;
        c = piArr[0];
    }

    public C1871zJ(int i) {
        super(8, i);
    }

    public static C1871zJ c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            return new C1871zJ(ri.c(b).b);
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        wi.u(c);
    }
}
