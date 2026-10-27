package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Pv extends AbstractC1596uJ implements InterfaceC1101lJ {
    public final InterfaceC1461rv s;

    public Pv(InterfaceC1461rv interfaceC1461rv) {
        this.s = interfaceC1461rv;
    }

    @Override // defpackage.InterfaceC1101lJ
    public boolean q(C0989jJ c0989jJ) {
        try {
            C1541tJ c1541tJA = c0989jJ.a();
            if (!c1541tJA.d.d(4, 2)) {
                return false;
            }
            Ov ovC = Ov.c(c1541tJA.b());
            ((Qv) this.s).a(ovC.b);
            return true;
        } catch (TI unused) {
            return false;
        }
    }
}
