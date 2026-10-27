package defpackage;

/* JADX INFO: renamed from: hF, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0874hF extends AbstractC1596uJ implements InterfaceC1101lJ {
    public final InterfaceC0595cF s;

    public C0874hF(InterfaceC0595cF interfaceC0595cF) {
        this.s = interfaceC0595cF;
    }

    @Override // defpackage.InterfaceC1101lJ
    public boolean q(C0989jJ c0989jJ) {
        try {
            C1541tJ c1541tJA = c0989jJ.a();
            if (!c1541tJA.d.d(0, 2)) {
                return false;
            }
            C0818gF c0818gFC = C0818gF.c(c1541tJA.b());
            ((C0930iF) this.s).a(c0818gFC.b);
            return true;
        } catch (TI unused) {
            return false;
        }
    }
}
