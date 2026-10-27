package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class UF extends AbstractC1596uJ implements InterfaceC1101lJ {
    public final InterfaceC1537tF s;

    public UF(InterfaceC1537tF interfaceC1537tF) {
        this.s = interfaceC1537tF;
    }

    @Override // defpackage.InterfaceC1101lJ
    public boolean q(C0989jJ c0989jJ) {
        try {
            C1541tJ c1541tJA = c0989jJ.a();
            if (!c1541tJA.d.d(2, 2)) {
                return false;
            }
            TF tfC = TF.c(c1541tJA.b());
            ((VF) this.s).a(tfC.b);
            return true;
        } catch (TI unused) {
            return false;
        }
    }
}
