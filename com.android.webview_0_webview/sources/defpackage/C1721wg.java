package defpackage;

/* JADX INFO: renamed from: wg, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1721wg {
    public static C1721wg b;
    public final C1666vg a = new C1666vg();

    static {
        C1721wg c1721wg = new C1721wg();
        synchronized (C1721wg.class) {
            b = c1721wg;
        }
    }

    public static C1666vg a() {
        C1721wg c1721wg;
        synchronized (C1721wg.class) {
            c1721wg = b;
        }
        return c1721wg.a;
    }
}
