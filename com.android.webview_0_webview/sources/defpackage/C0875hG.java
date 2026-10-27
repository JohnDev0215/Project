package defpackage;

/* JADX INFO: renamed from: hG, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0875hG extends AbstractC1596uJ implements InterfaceC1101lJ {
    public final InterfaceC1702wF s;

    public C0875hG(InterfaceC1702wF interfaceC1702wF) {
        this.s = interfaceC1702wF;
    }

    @Override // defpackage.InterfaceC1101lJ
    public boolean q(C0989jJ c0989jJ) {
        try {
            C1541tJ c1541tJA = c0989jJ.a();
            if (!c1541tJA.d.d(3, 2)) {
                return false;
            }
            C0819gG c0819gGC = C0819gG.c(c1541tJA.b());
            ((C0931iG) this.s).a(c0819gGC.b);
            return true;
        } catch (TI unused) {
            return false;
        }
    }
}
