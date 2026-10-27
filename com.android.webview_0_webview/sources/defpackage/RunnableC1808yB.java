package defpackage;

/* JADX INFO: renamed from: yB, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1808yB implements Runnable {
    public final /* synthetic */ int s;
    public final /* synthetic */ QB t;

    public RunnableC1808yB(QB qb, int i) {
        this.t = qb;
        this.s = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.e.W(this.s);
    }
}
