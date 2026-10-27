package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Pt implements Runnable {
    public final /* synthetic */ C1406qu s;
    public final /* synthetic */ Rt t;

    public Pt(Rt rt, C1406qu c1406qu) {
        this.t = rt;
        this.s = c1406qu;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.a.a(this.s);
    }
}
