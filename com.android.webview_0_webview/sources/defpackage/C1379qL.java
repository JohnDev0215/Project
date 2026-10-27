package defpackage;

/* JADX INFO: renamed from: qL, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1379qL extends AbstractC0766fJ {
    public C1379qL(LJ lj, InterfaceC1159mL interfaceC1159mL) {
        super(lj, interfaceC1159mL);
    }

    @Override // defpackage.InterfaceC1157mJ
    public boolean p(C0989jJ c0989jJ, InterfaceC1101lJ interfaceC1101lJ) {
        try {
            C1541tJ c1541tJA = c0989jJ.a();
            C1045kJ c1045kJ = c1541tJA.d;
            if (c1045kJ.c(1) && c1045kJ.b == -1) {
                return AbstractC0878hJ.a(this.s, AbstractC1433rL.a, c1541tJA, interfaceC1101lJ);
            }
            return false;
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
            if (!c1045kJ.c(0)) {
                return false;
            }
            int i = c1045kJ.b;
            if (i == -2) {
                return AbstractC0878hJ.b(AbstractC1433rL.a, c1541tJA);
            }
            if (i != 0) {
                return false;
            }
            C1269oL c1269oLC = C1269oL.c(c1541tJA.b());
            ((InterfaceC1159mL) this.t).l(c1269oLC.b, c1269oLC.c);
            return true;
        } catch (TI e) {
            System.err.println(e.toString());
            return false;
        }
    }
}
