package defpackage;

/* JADX INFO: renamed from: sI, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1485sI extends AbstractC0766fJ {
    public C1485sI(LJ lj, InterfaceC1266oI interfaceC1266oI) {
        super(lj, interfaceC1266oI);
    }

    @Override // defpackage.InterfaceC1157mJ
    public boolean p(C0989jJ c0989jJ, InterfaceC1101lJ interfaceC1101lJ) {
        try {
            C1541tJ c1541tJA = c0989jJ.a();
            C1045kJ c1045kJ = c1541tJA.d;
            if (c1045kJ.c(1) && c1045kJ.b == -1) {
                return AbstractC0878hJ.a(this.s, AbstractC1540tI.a, c1541tJA, interfaceC1101lJ);
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
                return AbstractC0878hJ.b(AbstractC1540tI.a, c1541tJA);
            }
            if (i != 0) {
                return false;
            }
            C1376qI c1376qIC = C1376qI.c(c1541tJA.b());
            ((InterfaceC1266oI) this.t).b(c1376qIC.b, c1376qIC.c, c1376qIC.d);
            return true;
        } catch (TI e) {
            System.err.println(e.toString());
            return false;
        }
    }
}
