package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class PL extends AbstractC0766fJ {
    public PL(LJ lj, HL hl) {
        super(lj, hl);
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
                return AbstractC0878hJ.a(this.s, QL.a, c1541tJA, interfaceC1101lJ);
            }
            if (i != 1) {
                return false;
            }
            KL.c(c1541tJA.b());
            ((HL) this.t).F(new NL(this.s, interfaceC1101lJ, c1045kJ.d));
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
            if (!c1045kJ.c(0)) {
                return false;
            }
            int i = c1045kJ.b;
            if (i == -2) {
                return AbstractC0878hJ.b(QL.a, c1541tJA);
            }
            if (i != 0) {
                return false;
            }
            JL jlC = JL.c(c1541tJA.b());
            ((HL) this.t).f(jlC.b, jlC.c);
            return true;
        } catch (TI e) {
            System.err.println(e.toString());
            return false;
        }
    }
}
