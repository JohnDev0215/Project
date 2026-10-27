package org.chromium.chrome.browser.tasks.tab_management;

import defpackage.g03;
import defpackage.k42;
import defpackage.l02;
import defpackage.n3;
import defpackage.oj2;
import defpackage.p13;
import defpackage.t13;
import defpackage.xl2;
import defpackage.y13;
import defpackage.z7;
import java.util.List;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.tasks.tab_management.i;
import org.chromium.chrome.browser.tasks.tab_management.s1;

/* JADX INFO: compiled from: TabSwitcherMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class u1 implements t13 {
    public final /* synthetic */ s1.b e;
    public final /* synthetic */ s1.c f;
    public final /* synthetic */ s1 g;

    public u1(s1 s1Var, s1.b bVar, s1.c cVar) {
        this.g = s1Var;
        this.e = bVar;
        this.f = cVar;
    }

    @Override // defpackage.t13
    public final void B0(int i, int i2, Tab tab) {
        s1 s1Var = this.g;
        if (s1Var.j.t()) {
            s1Var.l();
            if (i == 0 || s1Var.G || i == 5) {
                s1Var.G = false;
                return;
            }
            if (s1Var.K) {
                s1Var.K = false;
                y13 y13Var = s1Var.j;
                p13 p13VarC = y13Var.u().c();
                if (p13VarC instanceof g03) {
                    ((g03) p13VarC).V(tab);
                }
                if (i == 3) {
                    Tab tabF = z7.F(y13Var.z(), i2);
                    int i3 = s1Var.H;
                    int iA = y13Var.A();
                    int i4 = s1Var.M;
                    if (i3 != iA) {
                        if (z7.G(y13Var.z(), tab.getId()) == s1Var.f82J) {
                            n3.X0("MobileTabSwitched");
                        }
                        if (!oj2.D(s1Var.N) || s1Var.k(tab.getId()).size() == 1) {
                            n3.X0("MobileTabSwitched.GridTabSwitcher");
                        }
                    } else if (tab.getId() == s1Var.I) {
                        if (i4 == 2) {
                            n3.X0("MobileTabReturnedToCurrentTab.TabCarousel");
                        } else if (i4 == 0) {
                            n3.X0("MobileTabReturnedToCurrentTab.TabGrid");
                        }
                        n3.X0("MobileTabReturnedToCurrentTab");
                        oj2.a0(0, "Tabs.TabOffsetOfSwitch.GridTabSwitcher");
                    } else {
                        int iR = y13Var.u().c().r(tabF);
                        int iR2 = y13Var.u().c().r(tab);
                        if (iR != iR2) {
                            if (s1Var.k(tab.getId()).size() == 1) {
                                n3.X0("MobileTabSwitched.GridTabSwitcher");
                            }
                            oj2.a0(iR - iR2, "Tabs.TabOffsetOfSwitch.GridTabSwitcher");
                        }
                    }
                    if (i4 == 0) {
                        TabModel tabModelZ = y13Var.z();
                        SharedPreferencesManager sharedPreferencesManager = k42.a;
                        if ((!tabModelZ.f().h()) && k42.c()) {
                            int i5 = xl2.t;
                            xl2 xl2Var = (xl2) l02.d(tab, xl2.class);
                            n3.X0("Commerce.TabGridSwitched.".concat((xl2Var == null || xl2Var.q() == null) ? false : true ? "HasPriceDrop" : "NoPriceDrop"));
                        }
                    }
                }
            }
            if (s1Var.i.k(b0.a)) {
                s1Var.r(tab.getId(), false);
            }
        }
    }

    @Override // defpackage.t13
    public final void C(Tab tab) {
        s1 s1Var = this.g;
        if (s1Var.j.z().getCount() == 1) {
            ((o1) this.e).y();
        }
        i iVar = s1Var.D;
        if (iVar != null) {
            i.b bVar = iVar.j;
            if ((bVar == null ? -1 : bVar.a) == tab.getId()) {
                ((o1) this.f).u(3);
            }
        }
        s1Var.l();
    }

    @Override // defpackage.t13
    public final void V0(Tab tab) {
        this.g.l();
    }

    @Override // defpackage.t13
    public final void f1(Tab tab, int i, int i2, boolean z) {
        s1 s1Var = this.g;
        if (s1Var.j.t()) {
            s1Var.G = false;
        }
    }

    @Override // defpackage.t13
    public final void h0(Tab tab) {
        this.g.l();
    }

    @Override // defpackage.t13
    public final void i1(Tab tab) {
        s1 s1Var = this.g;
        i iVar = s1Var.D;
        if (iVar != null) {
            i.b bVar = iVar.j;
            if ((bVar == null ? -1 : bVar.a) == tab.getId()) {
                i iVar2 = s1Var.D;
                iVar2.j = null;
                iVar2.g();
            }
        }
    }

    @Override // defpackage.t13
    public final void n0(Tab tab) {
        this.g.l();
    }

    @Override // defpackage.t13
    public final void p0() {
        s1 s1Var = this.g;
        if (s1Var.i.k(b0.a)) {
            ((o1) s1Var.h).w(s1Var.j.u().c(), false, s1Var.L);
            s1Var.t();
        }
    }

    @Override // defpackage.t13
    public final void t(Tab tab, boolean z, boolean z2) {
        s1 s1Var = this.g;
        if (s1Var.j.z().getCount() == 1) {
            c0 c0Var = ((o1) this.e).i;
            c0Var.g(3, 5);
            c0Var.g(6, 4);
            return;
        }
        i iVar = s1Var.D;
        if (iVar != null) {
            i.b bVar = iVar.j;
            if ((bVar == null ? -1 : bVar.a) == tab.getId()) {
                ((o1) this.f).i.g(6, 3);
            }
        }
    }

    @Override // defpackage.t13
    public final void w0(List<Tab> list, boolean z) {
        this.g.l();
    }
}
