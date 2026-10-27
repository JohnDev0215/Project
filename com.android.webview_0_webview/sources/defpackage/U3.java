package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class U3 implements Runnable {
    public final /* synthetic */ V3 s;

    public U3(V3 v3) {
        this.s = v3;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.s.c.g() != null) {
            this.s.c.Y(null);
            V3 v3 = this.s;
            LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = v3.d;
            K3 k3 = v3.c;
            layoutInflaterFactory2C0974j4.c0(k3, k3.u(), 0, 0, false);
        }
    }
}
