package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Qt implements Runnable {
    public final /* synthetic */ C1406qu s;
    public final /* synthetic */ Rt t;

    public Qt(Rt rt, C1406qu c1406qu) {
        this.t = rt;
        this.s = c1406qu;
    }

    @Override // java.lang.Runnable
    public void run() {
        Runnable runnable;
        Xt xt = this.t.b;
        xt.d(this.s);
        if (xt.b.isEmpty()) {
            return;
        }
        ((Runnable) xt.b.remove()).run();
        if (xt.b.isEmpty() || (runnable = xt.a) == null) {
            return;
        }
        runnable.run();
    }
}
