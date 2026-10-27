package defpackage;

/* JADX INFO: renamed from: Zd, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0452Zd implements Runnable {
    public final /* synthetic */ C0416Xd s;

    public RunnableC0452Zd(C0416Xd c0416Xd) {
        this.s = c0416Xd;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.s.a.b.disconnect();
    }
}
