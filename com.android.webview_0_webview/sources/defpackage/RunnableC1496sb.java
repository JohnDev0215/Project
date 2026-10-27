package defpackage;

/* JADX INFO: renamed from: sb, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1496sb implements Runnable {
    public final /* synthetic */ C1551tb s;

    public RunnableC1496sb(C1551tb c1551tb) {
        this.s = c1551tb;
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this.s.n) {
            this.s.i();
        }
    }
}
