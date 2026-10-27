package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class KK implements Runnable {
    public final /* synthetic */ long s;
    public final /* synthetic */ int t;
    public final /* synthetic */ OK u;

    public KK(OK ok, long j, int i) {
        this.u = ok;
        this.s = j;
        this.t = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.u.b.d.c(this.s, this.t);
    }
}
