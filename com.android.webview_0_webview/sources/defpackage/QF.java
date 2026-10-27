package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class QF extends AbstractC1596uJ implements InterfaceC1101lJ {
    public final InterfaceC1482sF s;

    public QF(InterfaceC1482sF interfaceC1482sF) {
        this.s = interfaceC1482sF;
    }

    @Override // defpackage.InterfaceC1101lJ
    public boolean q(C0989jJ c0989jJ) {
        try {
            C1541tJ c1541tJA = c0989jJ.a();
            if (!c1541tJA.d.d(5, 2)) {
                return false;
            }
            PF pfC = PF.c(c1541tJA.b());
            ((RF) this.s).a(pfC.b);
            return true;
        } catch (TI unused) {
            return false;
        }
    }
}
