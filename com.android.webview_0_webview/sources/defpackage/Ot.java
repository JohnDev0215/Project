package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Ot implements Runnable {
    public final /* synthetic */ C1406qu s;
    public final /* synthetic */ Rt t;

    public Ot(Rt rt, C1406qu c1406qu) {
        this.t = rt;
        this.s = c1406qu;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.a.c(this.s);
    }
}
