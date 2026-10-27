package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.b;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* JADX INFO: compiled from: TabModelFilter.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class p13 implements t13, w03 {
    public static final List<Tab> i = Collections.unmodifiableList(new ArrayList());
    public static final List<Integer> j = Collections.unmodifiableList(new ArrayList());
    public final TabModel e;
    public final b<t13> f = new b<>();
    public boolean g;
    public boolean h;

    public p13(TabModel tabModel) {
        this.e = tabModel;
        tabModel.j(this);
    }

    public List<Integer> A(int i2) {
        if (z7.F(this.e, i2) == null) {
            return j;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(i2));
        return Collections.unmodifiableList(arrayList);
    }

    public List<Tab> B(int i2) {
        Tab tabF = z7.F(this.e, i2);
        if (tabF == null) {
            return i;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(tabF);
        return Collections.unmodifiableList(arrayList);
    }

    @Override // defpackage.t13
    public final void B0(int i2, int i3, Tab tab) {
        I(tab);
        if (!J()) {
            return;
        }
        Iterator<t13> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((t13) aVar.next()).B0(i2, i3, tab);
            }
        }
    }

    @Override // defpackage.t13
    public final void C(Tab tab) {
        y(tab);
        H();
        Iterator<t13> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((t13) aVar.next()).C(tab);
            }
        }
    }

    public final List<Tab> D() {
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (true) {
            TabModel tabModel = this.e;
            if (i2 >= tabModel.getCount()) {
                return Collections.unmodifiableList(arrayList);
            }
            Tab tabAt = tabModel.getTabAt(i2);
            if (!F(tabAt)) {
                arrayList.add(tabAt);
            }
            i2++;
        }
    }

    public abstract int E(int i2, Tab tab);

    public boolean F(Tab tab) {
        return false;
    }

    public void G() {
        this.h = true;
    }

    public abstract void H();

    public abstract void I(Tab tab);

    public boolean J() {
        return true;
    }

    @Override // defpackage.t13
    public final void P(boolean z) {
        Iterator<t13> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((t13) aVar.next()).P(z);
            }
        }
    }

    @Override // defpackage.t13
    public final void T() {
        Iterator<t13> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((t13) aVar.next()).T();
            }
        }
    }

    @Override // defpackage.t13
    public final void V0(Tab tab) {
        Iterator<t13> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((t13) aVar.next()).V0(tab);
            }
        }
    }

    @Override // defpackage.t13
    public final void X0(boolean z) {
        Iterator<t13> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((t13) aVar.next()).X0(z);
            }
        }
    }

    @Override // defpackage.t13
    public final void Y(List<Tab> list) {
        Iterator<t13> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((t13) aVar.next()).Y(list);
            }
        }
    }

    @Override // defpackage.t13
    public final void d1(int i2, Tab tab) {
        Iterator<t13> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((t13) aVar.next()).d1(i2, tab);
            }
        }
    }

    @Override // defpackage.t13
    public final void f1(Tab tab, int i2, int i3, boolean z) {
        y(tab);
        Iterator<t13> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((t13) aVar.next()).f1(tab, i2, i3, z);
            }
        }
    }

    @Override // defpackage.t13
    public final void h0(Tab tab) {
        Iterator<t13> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((t13) aVar.next()).h0(tab);
            }
        }
    }

    @Override // defpackage.t13
    public void h1(int i2, int i3, Tab tab) {
        Iterator<t13> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((t13) aVar.next()).h1(i2, i3, tab);
            }
        }
    }

    @Override // defpackage.t13
    public final void i1(Tab tab) {
        Iterator<t13> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((t13) aVar.next()).i1(tab);
            }
        }
    }

    @Override // defpackage.t13
    public final void n0(Tab tab) {
        o(tab);
        Iterator<t13> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((t13) aVar.next()).n0(tab);
            }
        }
    }

    public abstract void o(Tab tab);

    @Override // defpackage.t13
    public final void p0() {
        this.g = true;
        if (getCount() != 0) {
            H();
        }
        Iterator<t13> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((t13) aVar.next()).p0();
            }
        }
    }

    @Override // defpackage.t13
    public final void t(Tab tab, boolean z, boolean z2) {
        z(tab);
        Iterator<t13> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((t13) aVar.next()).t(tab, z, z2);
            }
        }
    }

    @Override // defpackage.t13
    public final void w0(List<Tab> list, boolean z) {
        Iterator<t13> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((t13) aVar.next()).w0(list, z);
            }
        }
    }

    public abstract void y(Tab tab);

    public abstract void z(Tab tab);
}
