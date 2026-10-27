package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class NK implements Runnable {
    public final /* synthetic */ int s;
    public final /* synthetic */ OK t;

    public NK(OK ok, int i) {
        this.t = ok;
        this.s = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.b.d.a(this.s);
    }
}
