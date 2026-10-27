package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class DF extends AbstractC0766fJ {
    public DF(LJ lj, InterfaceC1812yF interfaceC1812yF) {
        super(lj, interfaceC1812yF);
    }

    @Override // defpackage.InterfaceC1157mJ
    public boolean p(C0989jJ c0989jJ, InterfaceC1101lJ interfaceC1101lJ) {
        try {
            C1541tJ c1541tJA = c0989jJ.a();
            C1045kJ c1045kJ = c1541tJA.d;
            if (c1045kJ.c(1) && c1045kJ.b == -1) {
                return AbstractC0878hJ.a(this.s, EF.a, c1541tJA, interfaceC1101lJ);
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
                return AbstractC0878hJ.b(EF.a, c1541tJA);
            }
            if (i == 0) {
                BF bfC = BF.c(c1541tJA.b());
                ((CF) ((InterfaceC1812yF) this.t)).A(bfC.b, bfC.c, bfC.d);
                return true;
            }
            if (i != 1) {
                return false;
            }
            AF afC = AF.c(c1541tJA.b());
            ((CF) ((InterfaceC1812yF) this.t)).x(afC.b);
            return true;
        } catch (TI e) {
            System.err.println(e.toString());
            return false;
        }
    }
}
