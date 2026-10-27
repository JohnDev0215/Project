package defpackage;

import defpackage.a30;
import java.util.HashSet;
import java.util.Iterator;
import org.chromium.base.ThreadUtils;
import org.chromium.base.b;
import org.chromium.chrome.browser.metrics.PageLoadMetrics;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: TabObserverRegistrar.java */
/* JADX INFO: loaded from: classes.dex */
public final class o23 implements t13, v80 {
    public final a30 e;
    public final HashSet f = new HashSet();
    public final HashSet g = new HashSet();
    public final org.chromium.base.b<b> h = new org.chromium.base.b<>();
    public Tab i;

    /* JADX INFO: compiled from: TabObserverRegistrar.java */
    public class a extends a30.a {
        public a() {
        }

        @Override // a30.a
        public final void a() {
            o23 o23Var = o23.this;
            o23.a(o23Var);
            Iterator<b> it = o23Var.h.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    return;
                } else {
                    ((b) aVar.next()).y1();
                }
            }
        }

        @Override // a30.a
        public final void b(Tab tab) {
            o23 o23Var = o23.this;
            o23.a(o23Var);
            Iterator<b> it = o23Var.h.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    return;
                } else {
                    ((b) aVar.next()).z1(tab);
                }
            }
        }

        @Override // a30.a
        public final void c(Tab tab) {
            o23 o23Var = o23.this;
            o23.a(o23Var);
            Iterator<b> it = o23Var.h.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    return;
                } else {
                    ((b) aVar.next()).A1(tab);
                }
            }
        }
    }

    public o23(q2 q2Var, a30 a30Var) {
        a aVar = new a();
        this.e = a30Var;
        a30Var.a.a(aVar);
        q2Var.f(this);
    }

    public static void a(o23 o23Var) {
        Tab tab = o23Var.i;
        org.chromium.base.b<b> bVar = o23Var.h;
        if (tab != null) {
            Iterator<b> it = bVar.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    break;
                } else {
                    tab.t((n23) aVar.next());
                }
            }
        }
        Tab tab2 = o23Var.e.b;
        o23Var.i = tab2;
        if (tab2 == null) {
            return;
        }
        Iterator<b> it2 = bVar.iterator();
        while (true) {
            b.a aVar2 = (b.a) it2;
            if (!aVar2.hasNext()) {
                return;
            } else {
                tab2.x((n23) aVar2.next());
            }
        }
    }

    @Override // defpackage.t13
    public final void V0(Tab tab) {
        d();
    }

    public final void b(Tab tab) {
        for (PageLoadMetrics.a aVar : this.f) {
            org.chromium.base.b<PageLoadMetrics.a> bVar = PageLoadMetrics.a;
            Object obj = ThreadUtils.a;
            PageLoadMetrics.a.a(aVar);
        }
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            tab.x((n23) it.next());
        }
    }

    public final void c(b bVar) {
        this.h.a(bVar);
        Tab tab = this.e.b;
        if (tab != null) {
            tab.x(bVar);
            bVar.z1(tab);
        }
    }

    public final void d() {
        for (PageLoadMetrics.a aVar : this.f) {
            org.chromium.base.b<PageLoadMetrics.a> bVar = PageLoadMetrics.a;
            Object obj = ThreadUtils.a;
            PageLoadMetrics.a.c(aVar);
            PageLoadMetrics.b.c(aVar);
        }
    }

    public final void e(b bVar) {
        this.h.c(bVar);
        Tab tab = this.e.b;
        if (tab != null) {
            tab.t(bVar);
        }
    }

    @Override // defpackage.t13
    public final void f1(Tab tab, int i, int i2, boolean z) {
        b(tab);
    }

    @Override // defpackage.t13
    public final void n0(Tab tab) {
        d();
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            tab.t((n23) it.next());
        }
    }

    @Override // defpackage.v80
    public final void onDestroy() {
        d();
    }

    /* JADX INFO: compiled from: TabObserverRegistrar.java */
    public static abstract class b extends nh0 {
        public void y1() {
        }

        public void A1(Tab tab) {
        }

        public void z1(Tab tab) {
        }
    }
}
