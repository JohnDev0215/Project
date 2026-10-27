package defpackage;

/* JADX INFO: renamed from: yj, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC1834yj implements Runnable {
    public final /* synthetic */ AbstractC1559tj s;
    public final /* synthetic */ C1779xj t;

    public RunnableC1834yj(C1779xj c1779xj, AbstractC1559tj abstractC1559tj) {
        this.t = c1779xj;
        this.s = abstractC1559tj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.t.b) {
            if (this.t.c != null) {
                this.t.c.a(this.s);
            }
        }
    }
}
