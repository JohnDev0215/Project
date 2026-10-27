package defpackage;

/* JADX INFO: renamed from: rG, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1428rG extends AbstractC0766fJ {
    public C1428rG(LJ lj, InterfaceC1264oG interfaceC1264oG) {
        super(lj, interfaceC1264oG);
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
                return AbstractC0878hJ.a(this.s, AG.a, c1541tJA, interfaceC1101lJ);
            }
            if (i == 0) {
                ((InterfaceC1264oG) this.t).K(C1703wG.c(c1541tJA.b()).b, new C1868zG(this.s, interfaceC1101lJ, c1045kJ.d));
                return true;
            }
            if (i != 1) {
                return false;
            }
            C1483sG.c(c1541tJA.b());
            ((InterfaceC1264oG) this.t).B(new C1648vG(this.s, interfaceC1101lJ, c1045kJ.d));
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
                return AbstractC0878hJ.b(AG.a, c1541tJA);
            }
            return false;
        } catch (TI e) {
            System.err.println(e.toString());
            return false;
        }
    }
}
