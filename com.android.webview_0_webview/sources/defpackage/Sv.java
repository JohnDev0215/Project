package defpackage;

import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class Sv extends AbstractC0766fJ {
    public Sv(LJ lj, InterfaceC1516sv interfaceC1516sv) {
        super(lj, interfaceC1516sv);
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
                return AbstractC0878hJ.a(this.s, Tv.a, c1541tJA, interfaceC1101lJ);
            }
            if (i == 4) {
                Nv.c(c1541tJA.b());
                InterfaceC1516sv interfaceC1516sv = (InterfaceC1516sv) this.t;
                Qv qv = new Qv(this.s, interfaceC1101lJ, c1045kJ.d);
                Rv rv = (Rv) interfaceC1516sv;
                Objects.requireNonNull(rv);
                Nv nv = new Nv();
                C0487aJ c0487aJ = rv.s;
                c0487aJ.t.p(nv.b(c0487aJ.s, new C1045kJ(4, 1, 0L)), new Pv(qv));
                return true;
            }
            if (i == 5) {
                Cv.c(c1541tJA.b());
                InterfaceC1516sv interfaceC1516sv2 = (InterfaceC1516sv) this.t;
                Fv fv = new Fv(this.s, interfaceC1101lJ, c1045kJ.d);
                Rv rv2 = (Rv) interfaceC1516sv2;
                Objects.requireNonNull(rv2);
                Cv cv = new Cv();
                C0487aJ c0487aJ2 = rv2.s;
                c0487aJ2.t.p(cv.b(c0487aJ2.s, new C1045kJ(5, 1, 0L)), new Ev(fv));
                return true;
            }
            if (i != 6) {
                return false;
            }
            Hv.c(c1541tJA.b());
            InterfaceC1516sv interfaceC1516sv3 = (InterfaceC1516sv) this.t;
            Kv kv = new Kv(this.s, interfaceC1101lJ, c1045kJ.d);
            Rv rv3 = (Rv) interfaceC1516sv3;
            Objects.requireNonNull(rv3);
            Hv hv = new Hv();
            C0487aJ c0487aJ3 = rv3.s;
            c0487aJ3.t.p(hv.b(c0487aJ3.s, new C1045kJ(6, 1, 0L)), new Jv(kv));
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
                return AbstractC0878hJ.b(Tv.a, c1541tJA);
            }
            if (i == 0) {
                Gv gvC = Gv.c(c1541tJA.b());
                InterfaceC1516sv interfaceC1516sv = (InterfaceC1516sv) this.t;
                C0934iJ c0934iJ = gvC.b;
                Rv rv = (Rv) interfaceC1516sv;
                Objects.requireNonNull(rv);
                Gv gv = new Gv();
                gv.b = c0934iJ;
                C0487aJ c0487aJ = rv.s;
                c0487aJ.t.q(gv.b(c0487aJ.s, new C1045kJ(0)));
                return true;
            }
            if (i == 1) {
                Bv bvC = Bv.c(c1541tJA.b());
                InterfaceC1516sv interfaceC1516sv2 = (InterfaceC1516sv) this.t;
                C0934iJ c0934iJ2 = bvC.b;
                Rv rv2 = (Rv) interfaceC1516sv2;
                Objects.requireNonNull(rv2);
                Bv bv = new Bv();
                bv.b = c0934iJ2;
                C0487aJ c0487aJ2 = rv2.s;
                c0487aJ2.t.q(bv.b(c0487aJ2.s, new C1045kJ(1)));
                return true;
            }
            if (i == 2) {
                Lv lvC = Lv.c(c1541tJA.b());
                InterfaceC1516sv interfaceC1516sv3 = (InterfaceC1516sv) this.t;
                MJ mj = lvC.b;
                InterfaceC1571tv interfaceC1571tv = lvC.c;
                Rv rv3 = (Rv) interfaceC1516sv3;
                Objects.requireNonNull(rv3);
                Lv lv = new Lv();
                lv.b = mj;
                lv.c = interfaceC1571tv;
                C0487aJ c0487aJ3 = rv3.s;
                c0487aJ3.t.q(lv.b(c0487aJ3.s, new C1045kJ(2)));
                return true;
            }
            if (i != 3) {
                return false;
            }
            Mv mvC = Mv.c(c1541tJA.b());
            InterfaceC1516sv interfaceC1516sv4 = (InterfaceC1516sv) this.t;
            long j = mvC.b;
            long j2 = mvC.c;
            MJ mj2 = mvC.d;
            InterfaceC1571tv interfaceC1571tv2 = mvC.e;
            Rv rv4 = (Rv) interfaceC1516sv4;
            Objects.requireNonNull(rv4);
            Mv mv = new Mv();
            mv.b = j;
            mv.c = j2;
            mv.d = mj2;
            mv.e = interfaceC1571tv2;
            C0487aJ c0487aJ4 = rv4.s;
            c0487aJ4.t.q(mv.b(c0487aJ4.s, new C1045kJ(3)));
            return true;
        } catch (TI e) {
            System.err.println(e.toString());
            return false;
        }
    }
}
