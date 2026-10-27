package defpackage;

import defpackage.hz0;
import defpackage.lh0;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.content_public.browser.LoadUrlParams;

/* JADX INFO: compiled from: TabModelSelectorBase.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class a23 implements y13, hz0, o13 {
    public ez0 b;
    public final qr d;
    public int e;
    public hz0.a h;
    public boolean i;
    public boolean k;
    public final gz2 l;
    public final ArrayList a = new ArrayList();
    public final q13 c = new q13();
    public final b<e23> f = new b<>();
    public final b<hz0> g = new b<>();
    public boolean j = false;

    public a23(gz2 gz2Var, qr qrVar) {
        this.l = gz2Var;
        this.d = qrVar;
    }

    @Override // defpackage.y13
    public final int A() {
        return this.e;
    }

    public void B() {
        this.f.clear();
        q13 q13Var = this.c;
        l(q13Var);
        int i = 0;
        for (int i2 = 0; i2 < q13Var.e.size(); i2++) {
            q13Var.e.get(i2).f.clear();
        }
        q13Var.f.clear();
        ez0 ez0Var = this.b;
        if (ez0Var != null) {
            ((fz0) ez0Var).g.c(this);
        }
        while (true) {
            ArrayList arrayList = this.a;
            if (i >= arrayList.size()) {
                arrayList.clear();
                return;
            } else {
                ((TabModel) arrayList.get(i)).destroy();
                i++;
            }
        }
    }

    public final int C(boolean z) {
        int i = 0;
        while (true) {
            ArrayList arrayList = this.a;
            if (i >= arrayList.size()) {
                return -1;
            }
            if (z == ((TabModel) arrayList.get(i)).isIncognito()) {
                return i;
            }
            i++;
        }
    }

    public void D() {
        if (this.i) {
            return;
        }
        this.i = true;
        Iterator<e23> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((e23) aVar.next()).g();
            }
        }
    }

    public final void E() {
        Iterator<e23> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((e23) aVar.next()).b();
            }
        }
    }

    @Override // defpackage.y13
    public final boolean a(Tab tab) {
        int i = 0;
        while (true) {
            ArrayList arrayList = this.a;
            if (i >= arrayList.size()) {
                return false;
            }
            TabModel tabModel = (TabModel) arrayList.get(i);
            if (tabModel.r(tab) >= 0) {
                return tabModel.a(tab);
            }
            i++;
        }
    }

    @Override // defpackage.y13
    public final void b() {
        e(false);
    }

    @Override // defpackage.y13
    public void c() {
        int i = 0;
        while (true) {
            ArrayList arrayList = this.a;
            if (i >= arrayList.size()) {
                return;
            }
            ((TabModel) arrayList.get(i)).c();
            i++;
        }
    }

    @Override // defpackage.y13, defpackage.o13
    public void d(boolean z) {
        ArrayList arrayList = this.a;
        if (arrayList.size() == 0) {
            this.j = z;
            return;
        }
        int iC = C(z);
        if (iC == this.e) {
            return;
        }
        TabModel tabModel = (TabModel) arrayList.get(iC);
        ((TabModel) arrayList.get(this.e)).setActive(false);
        tabModel.setActive(true);
        this.e = iC;
        if (this.h != null && tabModel.isIncognito()) {
            ((iy0) this.h).k();
        }
        Iterator<e23> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((e23) aVar.next()).k(tabModel);
            }
        }
    }

    @Override // defpackage.y13
    public final void e(boolean z) {
        int i = 0;
        while (true) {
            ArrayList arrayList = this.a;
            if (i >= arrayList.size()) {
                return;
            }
            ((TabModel) arrayList.get(i)).e(z);
            i++;
        }
    }

    @Override // defpackage.hz0
    public final void f() {
        Iterator<hz0> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((hz0) aVar.next()).f();
            }
        }
    }

    @Override // defpackage.y13
    public final TabModel g(boolean z) {
        int iC = C(z);
        return iC == -1 ? lh0.a.a : (TabModel) this.a.get(iC);
    }

    @Override // defpackage.y13
    public final void h(hz0 hz0Var) {
        this.g.a(hz0Var);
    }

    @Override // defpackage.y13
    public final Tab i() {
        return z7.p(z());
    }

    @Override // defpackage.y13
    public final TabModel j(int i) {
        TabModel tabModel;
        int i2 = 0;
        while (true) {
            ArrayList arrayList = this.a;
            if (i2 >= arrayList.size()) {
                return null;
            }
            tabModel = (TabModel) arrayList.get(i2);
            if (z7.F(tabModel, i) != null || tabModel.w(i)) {
                break;
            }
            i2++;
        }
        return tabModel;
    }

    @Override // defpackage.y13
    public final int k() {
        Tab tabI = i();
        if (tabI != null) {
            return tabI.getId();
        }
        return -1;
    }

    @Override // defpackage.y13
    public final void l(e23 e23Var) {
        this.f.c(e23Var);
    }

    @Override // defpackage.y13
    public final void m(e23 e23Var) {
        b<e23> bVar = this.f;
        if (bVar.b(e23Var)) {
            return;
        }
        bVar.a(e23Var);
    }

    @Override // defpackage.y13
    public final void n() {
        this.k = true;
    }

    @Override // defpackage.y13
    public final int p() {
        int i = 0;
        int count = 0;
        while (true) {
            ArrayList arrayList = this.a;
            if (i >= arrayList.size()) {
                return count;
            }
            count += ((TabModel) arrayList.get(i)).getCount();
            i++;
        }
    }

    @Override // defpackage.hz0
    public final void q() {
        Iterator<hz0> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((hz0) aVar.next()).q();
            }
        }
    }

    @Override // defpackage.y13
    public final boolean r() {
        return this.a.size() == 0 ? this.j : z().isIncognito();
    }

    @Override // defpackage.y13
    public final Tab s(int i) {
        int i2 = 0;
        while (true) {
            ArrayList arrayList = this.a;
            if (i2 >= arrayList.size()) {
                return null;
            }
            Tab tabF = z7.F((w03) arrayList.get(i2), i);
            if (tabF != null) {
                return tabF;
            }
            i2++;
        }
    }

    @Override // defpackage.y13
    public final boolean t() {
        return this.i;
    }

    @Override // defpackage.y13
    public final q13 u() {
        return this.c;
    }

    @Override // defpackage.y13
    public final void v(hz0.a aVar) {
        this.h = aVar;
    }

    @Override // defpackage.y13
    public final void w(hz0 hz0Var) {
        this.g.c(hz0Var);
    }

    @Override // defpackage.y13
    public final Tab x(LoadUrlParams loadUrlParams, int i, Tab tab, boolean z) {
        return this.l.p0(z).b(i, tab, loadUrlParams);
    }

    @Override // defpackage.y13
    public final ArrayList y() {
        return this.a;
    }

    @Override // defpackage.y13
    public final TabModel z() {
        ArrayList arrayList = this.a;
        return arrayList.size() == 0 ? lh0.a.a : (TabModel) arrayList.get(this.e);
    }

    public void F(TabContentManager tabContentManager) {
    }
}
