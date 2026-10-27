package defpackage;

/* JADX INFO: renamed from: yG, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1813yG extends AbstractC1596uJ implements InterfaceC1101lJ {
    public final InterfaceC1209nG s;

    public C1813yG(InterfaceC1209nG interfaceC1209nG) {
        this.s = interfaceC1209nG;
    }

    @Override // defpackage.InterfaceC1101lJ
    public boolean q(C0989jJ c0989jJ) {
        try {
            if (!c0989jJ.a().d.d(0, 2)) {
                return false;
            }
            ((C1868zG) this.s).a();
            return true;
        } catch (TI unused) {
            return false;
        }
    }
}
