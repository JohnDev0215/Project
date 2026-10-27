package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Y0 extends AbstractC0525b1 {
    public static volatile Y0 c;
    public AbstractC0525b1 a;
    public AbstractC0525b1 b;

    public Y0() {
        C0469a1 c0469a1 = new C0469a1();
        this.b = c0469a1;
        this.a = c0469a1;
    }

    public static Y0 b() {
        if (c != null) {
            return c;
        }
        synchronized (Y0.class) {
            if (c == null) {
                c = new Y0();
            }
        }
        return c;
    }

    @Override // defpackage.AbstractC0525b1
    public boolean a() {
        return this.a.a();
    }
}
