package defpackage;

/* JADX INFO: renamed from: cG, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0596cG extends AbstractC1596uJ implements InterfaceC1101lJ {
    public final InterfaceC1647vF s;

    public C0596cG(InterfaceC1647vF interfaceC1647vF) {
        this.s = interfaceC1647vF;
    }

    @Override // defpackage.InterfaceC1101lJ
    public boolean q(C0989jJ c0989jJ) {
        try {
            C1541tJ c1541tJA = c0989jJ.a();
            if (!c1541tJA.d.d(1, 2)) {
                return false;
            }
            C0540bG c0540bGC = C0540bG.c(c1541tJA.b());
            ((C0652dG) this.s).a(c0540bGC.b);
            return true;
        } catch (TI unused) {
            return false;
        }
    }
}
