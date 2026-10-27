package defpackage;

import android.app.Activity;
import defpackage.bi1;
import defpackage.pr;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.toolbar.g;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: TabModalLifetimeHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public final class m13 implements hk1, v80, bi1.a, vb {
    public Activity f;
    public final q2 g;
    public final bi1 h;
    public final nw2<bw> i;
    public final nw2<l23> j;
    public final nw2<g> k;
    public final nw2<y13> l;
    public final nw2<mj> m;
    public final nw2<rq0> n;
    public final zb p;
    public pr q;
    public b r;
    public Tab s;
    public int t;
    public final a e = new a();
    public final aq1<Boolean> o = new aq1<>();

    /* JADX INFO: compiled from: TabModalLifetimeHandler.java */
    public class a extends nh0 {
        public a() {
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void F0(Tab tab) {
            m13 m13Var = m13.this;
            if (m13Var.s == tab) {
                m13Var.h.c(7);
                m13Var.s = null;
            }
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void S0(Tab tab, boolean z) {
            m13.this.s();
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void g1(Tab tab, GURL gurl) {
            m13 m13Var = m13.this;
            if (m13Var.s == tab) {
                m13Var.h.c(10);
            }
        }
    }

    /* JADX INFO: compiled from: TabModalLifetimeHandler.java */
    public class b extends h23 {
        public b(y13 y13Var) {
            super(y13Var);
        }

        @Override // defpackage.t13
        public final void B0(int i, int i2, Tab tab) {
            m13.this.r(tab);
        }
    }

    public m13(Activity activity, r2 r2Var, bi1 bi1Var, cs csVar, js jsVar, ks ksVar, g23 g23Var, ls lsVar, cs csVar2, zb zbVar) {
        this.f = activity;
        this.g = r2Var;
        r2Var.f(this);
        this.h = bi1Var;
        this.i = csVar;
        this.j = jsVar;
        this.t = -1;
        this.k = ksVar;
        this.n = csVar2;
        this.m = lsVar;
        new pr.a();
        this.l = g23Var;
        this.p = zbVar;
        if (zb.b()) {
            bi1Var.h.a(this);
            zbVar.a(this, 10);
        }
    }

    @Override // defpackage.hk1
    public final void G0() {
        y13 y13Var = this.l.get();
        this.q = new pr(this.f, this.j, this.k, this.n.get(), this.m.get(), y13Var);
        bw bwVar = this.i.get();
        pr.a aVar = this.q.r;
        bwVar.h.add(aVar);
        aVar.p(bwVar.i);
        this.h.a.put(0, this.q);
        r(y13Var.i());
        this.r = new b(y13Var);
    }

    @Override // bi1.a
    public final void e() {
        aq1<Boolean> aq1Var = this.o;
        pr prVar = this.q;
        aq1Var.r(Boolean.valueOf((prVar == null || prVar.f == null) ? false : true));
    }

    @Override // defpackage.vb
    public final zp1<Boolean> getHandleBackPressChangedSupplier() {
        return this.o;
    }

    @Override // bi1.a
    public final void k() {
        aq1<Boolean> aq1Var = this.o;
        pr prVar = this.q;
        aq1Var.r(Boolean.valueOf((prVar == null || prVar.f == null) ? false : true));
    }

    @Override // defpackage.vb
    public final int o() {
        pr prVar = this.q;
        int i = 1 ^ ((prVar == null || prVar.f == null) ? 0 : 1);
        prVar.d(5);
        return i;
    }

    @Override // defpackage.v80
    public final void onDestroy() {
        b bVar = this.r;
        if (bVar != null) {
            bVar.a();
        }
        pr prVar = this.q;
        if (prVar != null) {
            prVar.q.s(prVar);
        }
        Tab tab = this.s;
        if (tab != null) {
            tab.t(this.e);
            this.s = null;
        }
        zb zbVar = this.p;
        if (zbVar.f[10] != null) {
            this.h.h.c(this);
            zbVar.d(10);
        }
        this.g.e(this);
        this.f = null;
    }

    public final void r(Tab tab) {
        if (tab != this.s) {
            this.h.c(6);
            Tab tab2 = this.s;
            a aVar = this.e;
            if (tab2 != null) {
                tab2.t(aVar);
            }
            this.s = tab;
            if (tab != null) {
                tab.x(aVar);
                s();
            }
        }
    }

    public final void s() {
        boolean zIsUserInteractable = this.s.isUserInteractable();
        bi1 bi1Var = this.h;
        if (zIsUserInteractable) {
            bi1Var.g(0, this.t);
            this.t = -1;
        } else if (this.t == -1) {
            this.t = bi1Var.l(0);
        }
    }
}
