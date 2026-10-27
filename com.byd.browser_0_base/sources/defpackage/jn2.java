package defpackage;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.tasks.tab_management.h0;
import org.chromium.chrome.browser.tasks.tab_management.n1;
import org.chromium.chrome.browser.tasks.tab_management.q1;
import org.chromium.chrome.features.start_surface.StartSurfaceConfiguration;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: SingleTabSwitcherMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class jn2 implements n1.a {
    public final y13 f;
    public final PropertyModel g;
    public final h0 h;
    public final a i;
    public final b j;
    public n1.b l;
    public boolean m;
    public boolean n;
    public boolean o;
    public Long p;
    public boolean q;
    public final Context r;
    public final org.chromium.base.b<n1.d> e = new org.chromium.base.b<>();
    public final aq1<Boolean> k = new aq1<>();

    /* JADX INFO: compiled from: SingleTabSwitcherMediator.java */
    public class a implements t13 {
        public a() {
        }

        @Override // defpackage.t13
        public final void B0(int i, int i2, Tab tab) {
            jn2 jn2Var = jn2.this;
            if (zb2.e(jn2Var.r) || !jn2Var.f.r()) {
                jn2Var.n = false;
                jn2Var.d(tab);
                if (i == 0 || i == 5 || jn2Var.m) {
                    jn2Var.m = false;
                    return;
                }
                jn2Var.l.t(tab.getId(), SystemClock.uptimeMillis());
            }
        }
    }

    /* JADX INFO: compiled from: SingleTabSwitcherMediator.java */
    public class b implements e23 {
        public b() {
        }

        @Override // defpackage.e23
        public final void g() {
            jn2 jn2Var = jn2.this;
            TabModel tabModelG = jn2Var.f.g(false);
            if (jn2Var.o) {
                jn2Var.o = false;
                jn2Var.f.u().a(jn2Var.i);
            }
            int iIndex = tabModelG.index();
            if (iIndex != -1) {
                Tab tabAt = tabModelG.getTabAt(iIndex);
                jn2Var.g.p(kn2.d, tabAt.getTitle());
                if (jn2Var.p == null) {
                    jn2Var.p = Long.valueOf(SystemClock.elapsedRealtime());
                }
                h0 h0Var = jn2Var.h;
                if (h0Var.i) {
                    jn2Var.q = true;
                    GURL url = tabAt.getUrl();
                    aq3 aq3Var = new aq3(1, jn2Var);
                    h0Var.getClass();
                    h0Var.c(url, false, new ty2(aq3Var, 4));
                }
            }
        }

        @Override // defpackage.e23
        public final void k(TabModel tabModel) {
            if (tabModel.isIncognito()) {
                return;
            }
            jn2.this.m = true;
        }
    }

    /* JADX INFO: compiled from: SingleTabSwitcherMediator.java */
    public class c extends nh0 {
        public c() {
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void a1(Tab tab, GURL gurl) {
            jn2.this.g.p(kn2.d, tab.getTitle());
            tab.t(this);
        }
    }

    public jn2(Context context, PropertyModel propertyModel, y13 y13Var, h0 h0Var) {
        this.f = y13Var;
        this.g = propertyModel;
        this.h = h0Var;
        this.r = context;
        propertyModel.p(kn2.b, h0Var.e(false).a);
        propertyModel.p(kn2.a, new gs(14, this));
        this.i = new a();
        this.j = new b();
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.a
    public final aq1 E0() {
        return new aq1();
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.a
    public final int L() {
        return 2;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.a
    public final void Z0(boolean z) {
        this.n = true;
        y13 y13Var = this.f;
        y13Var.m(this.j);
        boolean zA = bq.F.a();
        PropertyModel propertyModel = this.g;
        if (!zA || y13Var.t()) {
            y13Var.u().a(this.i);
            TabModel tabModelG = y13Var.g(false);
            int iIndex = tabModelG.index();
            if (iIndex != -1) {
                d(tabModelG.getTabAt(iIndex));
                if (this.p == null) {
                    this.p = Long.valueOf(SystemClock.elapsedRealtime());
                }
            }
        } else {
            this.o = true;
            ku2 ku2VarT = ku2.t();
            try {
                g72.l(this.r);
                g72 g72Var = g72.g;
                ku2VarT.close();
                if (g72Var != null) {
                    propertyModel.p(kn2.d, g72Var.i());
                    if (this.p == null) {
                        this.p = Long.valueOf(SystemClock.elapsedRealtime());
                    }
                }
            } catch (Throwable th) {
                try {
                    ku2VarT.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        }
        propertyModel.l(kn2.c, true);
        org.chromium.base.b<n1.d> bVar = this.e;
        Iterator<n1.d> it = bVar.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                break;
            } else {
                ((n1.d) aVar.next()).s();
            }
        }
        Iterator<n1.d> it2 = bVar.iterator();
        while (true) {
            b.a aVar2 = (b.a) it2;
            if (!aVar2.hasNext()) {
                return;
            } else {
                ((n1.d) aVar2.next()).e();
            }
        }
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.a
    public final boolean a() {
        return false;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.a
    public final void b1(q1.a aVar) {
        this.e.c(aVar);
    }

    public final void d(Tab tab) {
        if (tab.k() && TextUtils.isEmpty(tab.getTitle())) {
            tab.x(new c());
        } else {
            this.g.p(kn2.d, tab.getTitle());
        }
        GURL url = tab.getUrl();
        se3 se3Var = new se3(12, this);
        h0 h0Var = this.h;
        h0Var.getClass();
        h0Var.c(url, false, new ty2(se3Var, 4));
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.a
    public final void g(boolean z) {
        this.m = false;
        y13 y13Var = this.f;
        y13Var.u().e(this.i);
        y13Var.l(this.j);
        PropertyModel.n nVar = kn2.c;
        PropertyModel propertyModel = this.g;
        propertyModel.l(nVar, false);
        propertyModel.p(kn2.b, this.h.e(false).a);
        propertyModel.p(kn2.d, "");
        org.chromium.base.b<n1.d> bVar = this.e;
        Iterator<n1.d> it = bVar.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                break;
            } else {
                ((n1.d) aVar.next()).k();
            }
        }
        Iterator<n1.d> it2 = bVar.iterator();
        while (true) {
            b.a aVar2 = (b.a) it2;
            if (!aVar2.hasNext()) {
                return;
            } else {
                ((n1.d) aVar2.next()).d();
            }
        }
    }

    @Override // defpackage.vb
    public final zp1<Boolean> getHandleBackPressChangedSupplier() {
        return this.k;
    }

    @Override // defpackage.vb
    public final int o() {
        return 1;
    }

    public void setOnTabSelectingListener(n1.b bVar) {
        this.l = bVar;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.a
    public final void u0(n1.d dVar) {
        this.e.a(dVar);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.a
    public final boolean w() {
        return false;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.a
    public final boolean w1() {
        return this.g.k(kn2.c);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.a
    public final void z0(long j) {
        Long l = this.p;
        if (l == null) {
            return;
        }
        StartSurfaceConfiguration.b(l.longValue() - j, "SingleTabTitleAvailableTime", oj2.f0(this.r, false));
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.a
    public final void t1() {
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.a
    public final void v0() {
    }
}
