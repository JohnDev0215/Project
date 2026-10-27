package defpackage;

/* JADX INFO: renamed from: uG, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1593uG extends AbstractC1596uJ implements InterfaceC1101lJ {
    public final InterfaceC1154mG s;

    public C1593uG(InterfaceC1154mG interfaceC1154mG) {
        this.s = interfaceC1154mG;
    }

    @Override // defpackage.InterfaceC1101lJ
    public boolean q(C0989jJ c0989jJ) {
        try {
            if (!c0989jJ.a().d.d(1, 2)) {
                return false;
            }
            ((C1648vG) this.s).a();
            return true;
        } catch (TI unused) {
            return false;
        }
    }
}
