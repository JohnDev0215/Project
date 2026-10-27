package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class LK implements Runnable {
    public final /* synthetic */ long s;
    public final /* synthetic */ OK t;

    public LK(OK ok, long j) {
        this.t = ok;
        this.s = j;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.b.d.f(this.s);
    }
}
