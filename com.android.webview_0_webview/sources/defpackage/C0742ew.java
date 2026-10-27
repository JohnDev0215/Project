package defpackage;

import java.util.Objects;

/* JADX INFO: renamed from: ew, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0742ew extends AbstractC0766fJ {
    public C0742ew(LJ lj, Wv wv) {
        super(lj, wv);
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
                return AbstractC0878hJ.a(this.s, AbstractC0798fw.a, c1541tJA, interfaceC1101lJ);
            }
            if (i != 0) {
                return false;
            }
            Zv.c(c1541tJA.b());
            Wv wv = (Wv) this.t;
            C0631cw c0631cw = new C0631cw(this.s, interfaceC1101lJ, c1045kJ.d);
            C0686dw c0686dw = (C0686dw) wv;
            Objects.requireNonNull(c0686dw);
            Zv zv = new Zv();
            C0487aJ c0487aJ = c0686dw.s;
            c0487aJ.t.p(zv.b(c0487aJ.s, new C1045kJ(0, 1, 0L)), new C0575bw(c0631cw));
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
                return AbstractC0878hJ.b(AbstractC0798fw.a, c1541tJA);
            }
            if (i != 1) {
                return false;
            }
            Yv yvC = Yv.c(c1541tJA.b());
            Wv wv = (Wv) this.t;
            C0934iJ c0934iJ = yvC.b;
            C0686dw c0686dw = (C0686dw) wv;
            Objects.requireNonNull(c0686dw);
            Yv yv = new Yv();
            yv.b = c0934iJ;
            C0487aJ c0487aJ = c0686dw.s;
            c0487aJ.t.q(yv.b(c0487aJ.s, new C1045kJ(1)));
            return true;
        } catch (TI e) {
            System.err.println(e.toString());
            return false;
        }
    }
}
