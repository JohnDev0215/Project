package defpackage;

import java.util.Iterator;
import java.util.List;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* JADX INFO: compiled from: UndoRefocusHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class oi3 extends h23 {
    public final /* synthetic */ qi3 g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public oi3(qi3 qi3Var, y13 y13Var) {
        super(y13Var);
        this.g = qi3Var;
    }

    @Override // defpackage.t13
    public final void B0(int i, int i2, Tab tab) {
        if (i == 3 || i == 4 || i == 2) {
            this.g.k = null;
        }
    }

    @Override // defpackage.t13
    public final void C(Tab tab) {
        int id = tab.getId();
        qi3 qi3Var = this.g;
        if (qi3Var.e.contains(Integer.valueOf(id))) {
            n3.X0("TabletTabStrip.UndoCloseTab");
            qi3Var.e.remove(Integer.valueOf(id));
        }
        Integer num = qi3Var.k;
        if (num == null || num.intValue() != id) {
            return;
        }
        qi3.a(qi3Var);
    }

    @Override // defpackage.t13
    public final void P(boolean z) {
        if (z) {
            return;
        }
        qi3 qi3Var = this.g;
        qi3Var.k = null;
        qi3Var.e.clear();
    }

    @Override // defpackage.t13
    public final void T() {
        qi3 qi3Var = this.g;
        if (qi3Var.k != null) {
            qi3.a(qi3Var);
        }
        qi3Var.k = null;
        qi3Var.e.clear();
    }

    @Override // defpackage.t13
    public final void X0(boolean z) {
        if (z) {
            return;
        }
        qi3 qi3Var = this.g;
        Tab tabAt = qi3Var.f.g(false).getTabAt(qi3Var.f.g(false).index());
        d(tabAt);
        if (qi3Var.l || !qi3Var.n) {
            return;
        }
        qi3Var.e.add(Integer.valueOf(tabAt.getId()));
    }

    public final boolean d(Tab tab) {
        Tab tabAt;
        qi3 qi3Var = this.g;
        TabModel tabModelG = qi3Var.f.g(false);
        int id = tab.getId();
        int iIndex = tabModelG.index();
        if (iIndex <= -1 || iIndex >= tabModelG.getCount() || (tabAt = tabModelG.getTabAt(iIndex)) == null || id != tabAt.getId()) {
            return false;
        }
        qi3Var.k = Integer.valueOf(id);
        return true;
    }

    @Override // defpackage.t13
    public final void i1(Tab tab) {
        if (tab.isIncognito()) {
            return;
        }
        qi3 qi3Var = this.g;
        qi3Var.k = null;
        qi3Var.e.clear();
    }

    @Override // defpackage.t13
    public final void k0(List list, boolean z) {
        if (!z || list.size() < 1) {
            return;
        }
        qi3 qi3Var = this.g;
        if (!qi3Var.l && qi3Var.n) {
            qi3Var.e.add(Integer.valueOf(((Tab) list.get(0)).getId()));
        }
        Iterator it = list.iterator();
        while (it.hasNext() && !d((Tab) it.next())) {
        }
    }

    @Override // defpackage.t13
    public final void t(Tab tab, boolean z, boolean z2) {
        if (!z2 || tab.isIncognito()) {
            return;
        }
        int id = tab.getId();
        qi3 qi3Var = this.g;
        if (!qi3Var.l && qi3Var.n) {
            qi3Var.e.add(Integer.valueOf(id));
        }
        d(tab);
    }
}
