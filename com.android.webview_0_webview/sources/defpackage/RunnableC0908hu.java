package defpackage;

/* JADX INFO: renamed from: hu, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0908hu implements Runnable {
    public final /* synthetic */ int s;
    public final /* synthetic */ BinderC1019ju t;

    public RunnableC0908hu(BinderC1019ju binderC1019ju, int i) {
        this.t = binderC1019ju;
        this.s = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        C1406qu c1406qu = this.t.a;
        int i = this.s;
        int i2 = c1406qu.l;
        if (i2 != 0) {
            Ts.a("ChildProcessConn", "sendPid was called more than once: pid=%d", Integer.valueOf(i2));
            return;
        }
        c1406qu.l = i;
        InterfaceC1241nu interfaceC1241nu = c1406qu.g;
        if (interfaceC1241nu != null) {
            ((C1625uu) interfaceC1241nu).a(c1406qu);
        }
        c1406qu.g = null;
    }
}
