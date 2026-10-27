package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class DM extends AbstractC1596uJ implements InterfaceC1101lJ {
    public final InterfaceC1654vM s;

    public DM(InterfaceC1654vM interfaceC1654vM) {
        this.s = interfaceC1654vM;
    }

    @Override // defpackage.InterfaceC1101lJ
    public boolean q(C0989jJ c0989jJ) {
        try {
            C1541tJ c1541tJA = c0989jJ.a();
            if (!c1541tJA.d.d(0, 2)) {
                return false;
            }
            CM cmC = CM.c(c1541tJA.b());
            ((EM) this.s).a(cmC.b);
            return true;
        } catch (TI unused) {
            return false;
        }
    }
}
