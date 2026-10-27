package defpackage;

/* JADX INFO: renamed from: f8, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0755f8 implements Runnable {
    public final /* synthetic */ String s;
    public final /* synthetic */ C0978j8 t;

    public RunnableC0755f8(C0978j8 c0978j8, String str) {
        this.t = c0978j8;
        this.s = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        C0212Lo c0212Lo = this.t.b;
        String strD = c0212Lo.d(this.s);
        if (strD != null) {
            c0212Lo.a.edit().remove(strD).apply();
        }
    }
}
