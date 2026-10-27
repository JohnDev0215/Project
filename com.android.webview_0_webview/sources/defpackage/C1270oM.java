package defpackage;

/* JADX INFO: renamed from: oM, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1270oM extends AbstractC1596uJ implements InterfaceC1101lJ {
    public final InterfaceC0602cM s;

    public C1270oM(InterfaceC0602cM interfaceC0602cM) {
        this.s = interfaceC0602cM;
    }

    @Override // defpackage.InterfaceC1101lJ
    public boolean q(C0989jJ c0989jJ) {
        try {
            C1541tJ c1541tJA = c0989jJ.a();
            if (!c1541tJA.d.d(0, 2)) {
                return false;
            }
            C1215nM c1215nMC = C1215nM.c(c1541tJA.b());
            ((C1325pM) this.s).a(c1215nMC.b);
            return true;
        } catch (TI unused) {
            return false;
        }
    }
}
