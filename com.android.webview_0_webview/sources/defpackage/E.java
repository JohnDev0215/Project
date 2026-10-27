package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class E implements Runnable {
    public final /* synthetic */ H s;

    public E(H h) {
        this.s = h;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.s.a(true);
        this.s.invalidateSelf();
    }
}
