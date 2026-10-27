package defpackage;

import defpackage.l61;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: ActivityTabProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class z2 extends aq1<Tab> implements w80 {
    public l61 h;
    public final a i = new a();
    public y13 j;
    public a3 k;
    public b3 l;

    /* JADX INFO: compiled from: ActivityTabProvider.java */
    public class a implements l61.a {
        public a() {
        }

        @Override // l61.a
        public final void e(int i) {
            z2 z2Var = z2.this;
            y13 y13Var = z2Var.j;
            if (y13Var == null) {
                return;
            }
            z2Var.r(y13Var.s(i));
        }

        @Override // l61.a
        public final void g(int i, boolean z) {
            if (8 == i) {
                return;
            }
            z2 z2Var = z2.this;
            Tab tabI = z2Var.j.i();
            if (i != 1) {
                tabI = null;
            }
            z2Var.s(tabI);
        }
    }

    @Override // defpackage.w80
    public final void destroy() {
        l61 l61Var = this.h;
        if (l61Var != null) {
            l61Var.f(this.i);
        }
        this.h = null;
        a3 a3Var = this.k;
        if (a3Var != null) {
            a3Var.a();
        }
        b3 b3Var = this.l;
        if (b3Var != null) {
            this.j.l(b3Var);
            this.l = null;
        }
        this.j = null;
    }

    public final void s(Tab tab) {
        l61 l61Var = this.h;
        if (l61Var == null || l61Var.q(1) || this.h.q(8) || tab == null) {
            r(tab);
        }
    }

    /* JADX INFO: compiled from: ActivityTabProvider.java */
    public static class b extends nh0 {
        public final z2 e;
        public final c3 f;
        public Tab g;

        public b(z2 z2Var, boolean z) {
            this.e = z2Var;
            c3 c3Var = new c3(0, this);
            this.f = c3Var;
            z2Var.p(c3Var);
            if (z) {
                y1((Tab) z2Var.f);
            }
            Tab tab = (Tab) z2Var.f;
            Tab tab2 = this.g;
            if (tab2 != null) {
                tab2.t(this);
            }
            this.g = tab;
            if (tab != null) {
                tab.x(this);
            }
        }

        public void destroy() {
            Tab tab = this.g;
            if (tab != null) {
                tab.t(this);
                this.g = null;
            }
            this.e.d(this.f);
        }

        public void y1(Tab tab) {
        }
    }
}
