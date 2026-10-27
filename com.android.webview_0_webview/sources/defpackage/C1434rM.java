package defpackage;

/* JADX INFO: renamed from: rM, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1434rM extends AbstractC0766fJ {
    public C1434rM(LJ lj, InterfaceC0658dM interfaceC0658dM) {
        super(lj, interfaceC0658dM);
    }

    @Override // defpackage.InterfaceC1157mJ
    public boolean p(C0989jJ c0989jJ, InterfaceC1101lJ interfaceC1101lJ) {
        try {
            C1541tJ c1541tJA = c0989jJ.a();
            C1045kJ c1045kJ = c1541tJA.d;
            if (!c1045kJ.c(1)) {
                return false;
            }
            int i = c1045kJ.b;
            if (i == -1) {
                return AbstractC0878hJ.a(this.s, AbstractC1489sM.a, c1541tJA, interfaceC1101lJ);
            }
            if (i != 0) {
                return false;
            }
            ((InterfaceC0658dM) this.t).g(C1160mM.c(c1541tJA.b()).b, new C1325pM(this.s, interfaceC1101lJ, c1045kJ.d));
            return true;
        } catch (TI e) {
            System.err.println(e.toString());
            return false;
        }
    }

    @Override // defpackage.InterfaceC1101lJ
    public boolean q(C0989jJ c0989jJ) {
        try {
            C1541tJ c1541tJA = c0989jJ.a();
            C1045kJ c1045kJ = c1541tJA.d;
            if (c1045kJ.c(0) && c1045kJ.b == -2) {
                return AbstractC0878hJ.b(AbstractC1489sM.a, c1541tJA);
            }
            return false;
        } catch (TI e) {
            System.err.println(e.toString());
            return false;
        }
    }
}
