package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Ev extends AbstractC1596uJ implements InterfaceC1101lJ {
    public final InterfaceC1352pv s;

    public Ev(InterfaceC1352pv interfaceC1352pv) {
        this.s = interfaceC1352pv;
    }

    @Override // defpackage.InterfaceC1101lJ
    public boolean q(C0989jJ c0989jJ) {
        try {
            C1541tJ c1541tJA = c0989jJ.a();
            if (!c1541tJA.d.d(5, 2)) {
                return false;
            }
            Dv dvC = Dv.c(c1541tJA.b());
            ((Fv) this.s).a(Long.valueOf(dvC.b), dvC.c);
            return true;
        } catch (TI unused) {
            return false;
        }
    }
}
