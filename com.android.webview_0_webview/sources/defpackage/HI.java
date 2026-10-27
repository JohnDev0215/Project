package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class HI implements Runnable {
    public final /* synthetic */ II s;

    public HI(II ii) {
        this.s = ii;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.s.close();
    }
}
