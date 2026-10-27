package defpackage;

/* JADX INFO: renamed from: e8, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0699e8 implements Runnable {
    public final /* synthetic */ String s;
    public final /* synthetic */ C0978j8 t;

    public RunnableC0699e8(C0978j8 c0978j8, String str) {
        this.t = c0978j8;
        this.s = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.b.a(this.s);
    }
}
