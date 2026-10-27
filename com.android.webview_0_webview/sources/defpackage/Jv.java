package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Jv extends AbstractC1596uJ implements InterfaceC1101lJ {
    public final InterfaceC1407qv s;

    public Jv(InterfaceC1407qv interfaceC1407qv) {
        this.s = interfaceC1407qv;
    }

    @Override // defpackage.InterfaceC1101lJ
    public boolean q(C0989jJ c0989jJ) {
        try {
            C1541tJ c1541tJA = c0989jJ.a();
            if (!c1541tJA.d.d(6, 2)) {
                return false;
            }
            Iv ivC = Iv.c(c1541tJA.b());
            ((Kv) this.s).a(ivC.b);
            return true;
        } catch (TI unused) {
            return false;
        }
    }
}
