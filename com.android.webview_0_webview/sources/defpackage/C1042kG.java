package defpackage;

/* JADX INFO: renamed from: kG, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1042kG extends AbstractC0766fJ {
    public C1042kG(LJ lj, InterfaceC1757xF interfaceC1757xF) {
        super(lj, interfaceC1757xF);
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
                return AbstractC0878hJ.a(this.s, AbstractC1098lG.a, c1541tJA, interfaceC1101lJ);
            }
            if (i == 1) {
                C0484aG c0484aGC = C0484aG.c(c1541tJA.b());
                ((InterfaceC1757xF) this.t).r(c0484aGC.b, c0484aGC.c, new C0652dG(this.s, interfaceC1101lJ, c1045kJ.d));
                return true;
            }
            if (i == 2) {
                SF.c(c1541tJA.b());
                ((InterfaceC1757xF) this.t).o(new VF(this.s, interfaceC1101lJ, c1045kJ.d));
                return true;
            }
            if (i == 3) {
                C0763fG c0763fGC = C0763fG.c(c1541tJA.b());
                ((InterfaceC1757xF) this.t).h(c0763fGC.b, c0763fGC.c, new C0931iG(this.s, interfaceC1101lJ, c1045kJ.d));
                return true;
            }
            if (i == 4) {
                ((InterfaceC1757xF) this.t).s(WF.c(c1541tJA.b()).b, new ZF(this.s, interfaceC1101lJ, c1045kJ.d));
                return true;
            }
            if (i != 5) {
                return false;
            }
            OF.c(c1541tJA.b());
            ((InterfaceC1757xF) this.t).D(new RF(this.s, interfaceC1101lJ, c1045kJ.d));
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
                return AbstractC0878hJ.b(AbstractC1098lG.a, c1541tJA);
            }
            if (i != 0) {
                return false;
            }
            ((InterfaceC1757xF) this.t).k(C0707eG.c(c1541tJA.b()).b);
            return true;
        } catch (TI e) {
            System.err.println(e.toString());
            return false;
        }
    }
}
