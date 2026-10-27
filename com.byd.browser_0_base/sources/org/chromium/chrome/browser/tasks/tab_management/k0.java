package org.chromium.chrome.browser.tasks.tab_management;

import android.util.Log;
import defpackage.g03;
import defpackage.g72;
import defpackage.mh0;
import defpackage.n3;
import defpackage.p13;
import defpackage.t13;
import java.util.HashMap;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: TabListMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class k0 implements t13 {
    public final /* synthetic */ j0 e;

    public k0(j0 j0Var) {
        this.e = j0Var;
    }

    @Override // defpackage.t13
    public final void B0(int i, int i2, Tab tab) {
        j0 j0Var = this.e;
        j0Var.u = -1;
        if (tab.getId() == i2) {
            return;
        }
        n0 n0Var = j0Var.f;
        int iF = n0Var.F(i2);
        int iF2 = n0Var.F(tab.getId());
        if (iF2 == -1 && j0Var.r && i == 5) {
            iF2 = j0.a(j0Var, tab);
        }
        j0Var.H = iF;
        Tab tab2 = j0Var.c;
        if (tab2 == null || tab2 != tab) {
            j0Var.k(iF, iF2);
        }
    }

    @Override // defpackage.t13
    public final void C(Tab tab) {
        p13 p13VarC;
        int iR;
        j0 j0Var = this.e;
        j0Var.j(tab, !j0Var.r);
        HashMap map = j0.f79J;
        if (map.containsKey(Integer.valueOf(tab.getId()))) {
            int iIntValue = ((Integer) map.get(Integer.valueOf(tab.getId()))).intValue();
            if (iIntValue == 0) {
                n3.X0("TabStrip.UndoCloseTab");
            } else if (iIntValue == 2) {
                n3.X0("GridTabSwitch.UndoCloseTab");
            } else if (iIntValue == 3) {
                n3.X0("GridTabSwitcher.UndoCloseTabGroup");
            }
            map.remove(Integer.valueOf(tab.getId()));
        }
        if (!j0Var.r || (iR = (p13VarC = j0Var.h.u().c()).r(tab)) == -1 || j0Var.f(tab.getId()).size() == 1) {
            return;
        }
        n0 n0Var = j0Var.f;
        if (iR >= n0Var.size()) {
            return;
        }
        Tab tabAt = p13VarC.getTabAt(iR);
        int iG = n0Var.G(iR);
        int iF = n0Var.F(tabAt.getId());
        Log.i("test", "tabClosureUndone:   indexFromId  " + iF + "  tabListModelIndex  " + iG);
        if ((iF == iG || iF < 0) && iG >= 0 && iG < n0Var.size()) {
            this.e.r(iG, g72.a(tabAt), n0Var.get(iG).b.k(u0.k), false, false);
        }
    }

    @Override // defpackage.t13
    public final void f1(Tab tab, int i, int i2, boolean z) {
        p13 p13VarC;
        int iR;
        j0 j0Var = this.e;
        if (j0Var.h.t()) {
            if (i == 19 && z && "GridTabSwitcher".equals(j0Var.p)) {
                j0Var.c = tab;
                return;
            }
            boolean z2 = j0Var.r;
            j0Var.j(tab, !z2);
            if (i == 3 && z2 && (iR = (p13VarC = j0Var.h.u().c()).r(tab)) != -1) {
                Tab tabAt = p13VarC.getTabAt(iR);
                n0 n0Var = j0Var.f;
                int iG = n0Var.G(iR);
                if (n0Var.F(tabAt.getId()) != iG) {
                    return;
                }
                this.e.r(iG, g72.a(tabAt), n0Var.get(iG).b.k(u0.k), false, false);
            }
        }
    }

    @Override // defpackage.t13
    public final void h1(int i, int i2, Tab tab) {
        j0 j0Var = this.e;
        if (j0Var.h.u().c() instanceof g03) {
            return;
        }
        int iG = j0Var.f.G(i);
        int iG2 = j0Var.f.G(i2);
        if ((j0Var.h.u().c() instanceof mh0) && j0Var.i(iG2) && j0Var.i(iG)) {
            j0Var.f.w(iG2, iG);
        }
    }

    @Override // defpackage.t13
    public final void n0(Tab tab) {
        j0 j0Var = this.e;
        if (j0Var.f.F(tab.getId()) == -1) {
            return;
        }
        int id = tab.getId();
        n0 n0Var = j0Var.f;
        n0Var.y(n0Var.F(id));
    }

    @Override // defpackage.t13
    public final void t(Tab tab, boolean z, boolean z2) {
        j0 j0Var = this.e;
        if (j0Var.f.F(tab.getId()) == -1) {
            return;
        }
        tab.t(j0Var.B);
        int id = tab.getId();
        n0 n0Var = j0Var.f;
        n0Var.y(n0Var.F(id));
    }
}
