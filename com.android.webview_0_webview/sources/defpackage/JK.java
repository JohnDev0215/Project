package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class JK implements Runnable {
    public final /* synthetic */ long s;
    public final /* synthetic */ int t;
    public final /* synthetic */ boolean u;
    public final /* synthetic */ OK v;

    public JK(OK ok, long j, int i, boolean z) {
        this.v = ok;
        this.s = j;
        this.t = i;
        this.u = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.v.b.d.c(this.s, this.t);
        if (this.u) {
            this.v.b.d.a(this.t);
            this.v.b.d.b(new long[]{this.s});
        }
    }
}
