package defpackage;

/* JADX INFO: renamed from: oQ, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1274oQ implements Runnable {
    public final /* synthetic */ RD s;
    public final /* synthetic */ C1329pQ t;

    public RunnableC1274oQ(C1329pQ c1329pQ, RD rd) {
        this.t = c1329pQ;
        this.s = rd;
    }

    @Override // java.lang.Runnable
    public void run() {
        C1329pQ c1329pQ = this.t;
        if (!c1329pQ.c) {
            c1329pQ.b.z(this.s);
        }
    }
}
