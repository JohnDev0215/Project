package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Dz implements InterfaceC1351pu {
    public final /* synthetic */ Fz a;

    public Dz(Fz fz) {
        this.a = fz;
    }

    @Override // defpackage.InterfaceC1351pu
    public void a(C1406qu c1406qu) {
        InterfaceC1351pu interfaceC1351pu = this.a.d;
        if (interfaceC1351pu != null) {
            interfaceC1351pu.a(c1406qu);
        }
        Fz fz = this.a;
        if (fz.b != null) {
            fz.b = null;
            fz.c = false;
        }
    }

    @Override // defpackage.InterfaceC1351pu
    public void b() {
        Fz fz = this.a;
        fz.c = true;
        InterfaceC1351pu interfaceC1351pu = fz.d;
        if (interfaceC1351pu != null) {
            interfaceC1351pu.b();
            Fz.a(this.a);
        }
    }

    @Override // defpackage.InterfaceC1351pu
    public void c(C1406qu c1406qu) {
        Ts.a("SpareChildConn", "Failed to warm up the spare sandbox service", new Object[0]);
        InterfaceC1351pu interfaceC1351pu = this.a.d;
        if (interfaceC1351pu != null) {
            interfaceC1351pu.c(c1406qu);
        }
        Fz.a(this.a);
    }
}
