package defpackage;

/* JADX INFO: renamed from: yJ, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1816yJ extends AbstractC1651vJ {
    public static final PI[] b;
    public static final PI c;

    static {
        PI[] piArr = {new PI(8, 0)};
        b = piArr;
        c = piArr[0];
    }

    public C1816yJ(int i) {
        super(8, i);
    }

    public static C1816yJ c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            return new C1816yJ(ri.c(b).b);
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        wi.u(c);
    }
}
