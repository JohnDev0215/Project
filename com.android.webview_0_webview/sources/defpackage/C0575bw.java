package defpackage;

/* JADX INFO: renamed from: bw, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0575bw extends AbstractC1596uJ implements InterfaceC1101lJ {
    public final Vv s;

    public C0575bw(Vv vv) {
        this.s = vv;
    }

    @Override // defpackage.InterfaceC1101lJ
    public boolean q(C0989jJ c0989jJ) {
        try {
            C1541tJ c1541tJA = c0989jJ.a();
            if (!c1541tJA.d.d(0, 2)) {
                return false;
            }
            C0519aw c0519awC = C0519aw.c(c1541tJA.b());
            ((C0631cw) this.s).a(c0519awC.b);
            return true;
        } catch (TI unused) {
            return false;
        }
    }
}
