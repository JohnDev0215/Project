package defpackage;

/* JADX INFO: renamed from: lI, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1100lI extends AbstractC0766fJ {
    public C1100lI(LJ lj, InterfaceC0765fI interfaceC0765fI) {
        super(lj, interfaceC0765fI);
    }

    @Override // defpackage.InterfaceC1157mJ
    public boolean p(C0989jJ c0989jJ, InterfaceC1101lJ interfaceC1101lJ) {
        try {
            C1541tJ c1541tJA = c0989jJ.a();
            C1045kJ c1045kJ = c1541tJA.d;
            if (c1045kJ.c(1) && c1045kJ.b == -1) {
                return AbstractC0878hJ.a(this.s, AbstractC1156mI.a, c1541tJA, interfaceC1101lJ);
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
                return AbstractC0878hJ.b(AbstractC1156mI.a, c1541tJA);
            }
            if (i == 0) {
                C0988jI c0988jIC = C0988jI.c(c1541tJA.b());
                ((C1044kI) ((InterfaceC0765fI) this.t)).G(c0988jIC.b);
                return true;
            }
            if (i == 1) {
                C0877hI.c(c1541tJA.b());
                ((C1044kI) ((InterfaceC0765fI) this.t)).x();
                return true;
            }
            if (i != 2) {
                return false;
            }
            C0933iI c0933iIC = C0933iI.c(c1541tJA.b());
            ((C1044kI) ((InterfaceC0765fI) this.t)).A(c0933iIC.b);
            return true;
        } catch (TI e) {
            System.err.println(e.toString());
            return false;
        }
    }
}
