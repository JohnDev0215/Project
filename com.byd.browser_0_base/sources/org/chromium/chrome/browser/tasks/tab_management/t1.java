package org.chromium.chrome.browser.tasks.tab_management;

import defpackage.e23;
import defpackage.p13;
import defpackage.qs1;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.tasks.tab_management.m;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: TabSwitcherMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class t1 implements e23 {
    public final /* synthetic */ s1 e;

    public t1(s1 s1Var) {
        this.e = s1Var;
    }

    @Override // defpackage.e23
    public final void k(TabModel tabModel) {
        s1 s1Var = this.e;
        s1Var.G = true;
        s1Var.f82J = tabModel.index();
        p13 p13VarC = s1Var.j.u().c();
        PropertyModel.n nVar = b0.b;
        boolean zIsIncognito = p13VarC.isIncognito();
        PropertyModel propertyModel = s1Var.i;
        propertyModel.l(nVar, zIsIncognito);
        s1Var.l();
        qs1<m.b> qs1Var = s1Var.A;
        if (qs1Var != null && qs1Var.f()) {
            qs1Var.get().W(false);
        }
        if (propertyModel.k(b0.a) && !s1Var.j()) {
            ((o1) s1Var.h).w(p13VarC, false, s1Var.L);
            s1Var.t();
        }
    }
}
