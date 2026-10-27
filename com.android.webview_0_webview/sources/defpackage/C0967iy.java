package defpackage;

/* JADX INFO: renamed from: iy, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0967iy implements InterfaceC1656vO {
    public final /* synthetic */ Runnable a;
    public final /* synthetic */ C1546tO b;
    public final /* synthetic */ Runnable c;

    public C0967iy(Runnable runnable, C1546tO c1546tO, Runnable runnable2) {
        this.a = runnable;
        this.b = c1546tO;
        this.c = runnable2;
    }

    @Override // defpackage.InterfaceC1656vO
    public void b(JO jo, int i) {
        if (i != 1) {
            this.c.run();
        }
    }

    @Override // defpackage.InterfaceC1656vO
    public void d(JO jo, int i) {
        if (i == 0) {
            this.a.run();
            this.b.b(jo, 1);
        }
    }
}
