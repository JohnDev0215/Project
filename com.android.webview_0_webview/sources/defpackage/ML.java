package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ML extends AbstractC1596uJ implements InterfaceC1101lJ {
    public final GL s;

    public ML(GL gl) {
        this.s = gl;
    }

    @Override // defpackage.InterfaceC1101lJ
    public boolean q(C0989jJ c0989jJ) {
        try {
            C1541tJ c1541tJA = c0989jJ.a();
            if (!c1541tJA.d.d(1, 2)) {
                return false;
            }
            LL llC = LL.c(c1541tJA.b());
            ((NL) this.s).a(llC.b);
            return true;
        } catch (TI unused) {
            return false;
        }
    }
}
