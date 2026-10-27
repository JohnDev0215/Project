package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class VL extends AbstractC1596uJ implements InterfaceC1101lJ {
    public final EL s;

    public VL(EL el) {
        this.s = el;
    }

    @Override // defpackage.InterfaceC1101lJ
    public boolean q(C0989jJ c0989jJ) {
        try {
            C1541tJ c1541tJA = c0989jJ.a();
            if (!c1541tJA.d.d(0, 2)) {
                return false;
            }
            UL ulC = UL.c(c1541tJA.b());
            ((WL) this.s).a(ulC.b);
            return true;
        } catch (TI unused) {
            return false;
        }
    }
}
