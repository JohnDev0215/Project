package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Rt implements InterfaceC1351pu {
    public final /* synthetic */ InterfaceC1351pu a;
    public final /* synthetic */ Xt b;

    public Rt(Xt xt, InterfaceC1351pu interfaceC1351pu) {
        this.b = xt;
        this.a = interfaceC1351pu;
    }

    @Override // defpackage.InterfaceC1351pu
    public void a(C1406qu c1406qu) {
        if (this.a != null) {
            this.b.c.post(new Pt(this, c1406qu));
        }
        this.b.c.postDelayed(new Qt(this, c1406qu), 1L);
    }

    @Override // defpackage.InterfaceC1351pu
    public void b() {
        if (this.a != null) {
            this.b.c.post(new Nt(this));
        }
    }

    @Override // defpackage.InterfaceC1351pu
    public void c(C1406qu c1406qu) {
        if (this.a != null) {
            this.b.c.post(new Ot(this, c1406qu));
        }
        this.b.c.postDelayed(new Qt(this, c1406qu), 1L);
    }
}
