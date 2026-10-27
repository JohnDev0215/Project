package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class YF extends AbstractC1596uJ implements InterfaceC1101lJ {
    public final InterfaceC1592uF s;

    public YF(InterfaceC1592uF interfaceC1592uF) {
        this.s = interfaceC1592uF;
    }

    @Override // defpackage.InterfaceC1101lJ
    public boolean q(C0989jJ c0989jJ) {
        try {
            C1541tJ c1541tJA = c0989jJ.a();
            if (!c1541tJA.d.d(4, 2)) {
                return false;
            }
            XF xfC = XF.c(c1541tJA.b());
            ((ZF) this.s).a(xfC.b);
            return true;
        } catch (TI unused) {
            return false;
        }
    }
}
