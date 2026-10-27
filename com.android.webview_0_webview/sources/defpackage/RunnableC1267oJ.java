package defpackage;

/* JADX INFO: renamed from: oJ, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1267oJ implements Runnable {
    public final /* synthetic */ C1431rJ s;

    public RunnableC1267oJ(C1431rJ c1431rJ) {
        this.s = c1431rJ;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.s.s.close();
    }
}
