package org.chromium.chrome.browser.tasks.tab_management;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import defpackage.ai;
import defpackage.aq1;
import defpackage.bi;
import defpackage.bz0;
import defpackage.c01;
import defpackage.ci;
import defpackage.dr;
import defpackage.g03;
import defpackage.gs;
import defpackage.gz2;
import defpackage.ie0;
import defpackage.j23;
import defpackage.jj;
import defpackage.l03;
import defpackage.l61;
import defpackage.ll;
import defpackage.m03;
import defpackage.n03;
import defpackage.nw2;
import defpackage.oj2;
import defpackage.on0;
import defpackage.qs1;
import defpackage.vb;
import defpackage.y13;
import defpackage.zp1;
import java.util.List;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.m;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: TabGroupUiMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a0 implements vb {
    public final Context e;
    public final PropertyModel f;
    public final v g;
    public final a h;
    public final y13 i;
    public final gz2 j;
    public final ai.a k;
    public final bz0 l;
    public final nw2<m.b> m;
    public final l03 n;
    public final y o;
    public final zp1<Boolean> p;
    public ll q = new ll();
    public final w r;
    public l61 s;
    public final z t;
    public j23 u;
    public final dr v;
    public boolean w;
    public boolean x;

    /* JADX INFO: compiled from: TabGroupUiMediator.java */
    public interface a {
    }

    /* JADX INFO: compiled from: TabGroupUiMediator.java */
    public interface b {
        void setupLeftButtonOnClickListener(View.OnClickListener onClickListener);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v3, types: [bz0$b, l03] */
    public a0(Activity activity, c01 c01Var, a aVar, PropertyModel propertyModel, y13 y13Var, gz2 gz2Var, qs1 qs1Var, bz0 bz0Var, nw2 nw2Var, zp1 zp1Var) {
        this.e = activity;
        this.h = aVar;
        this.f = propertyModel;
        this.i = y13Var;
        this.j = gz2Var;
        this.k = c01Var;
        this.l = bz0Var;
        this.m = nw2Var;
        this.p = zp1Var;
        if (qs1Var.get() != null && (((l61) qs1Var.get()).q(2) || ((l61) qs1Var.get()).q(16))) {
            this.x = true;
        }
        v vVar = new v(this);
        this.g = vVar;
        this.r = new w(this);
        this.u = new x(this, y13Var);
        y yVar = new y(this);
        this.o = yVar;
        if (oj2.D(activity)) {
            z zVar = new z(this);
            this.t = zVar;
            ((g03) y13Var.u().d(false)).K(zVar);
            ((g03) y13Var.u().d(true)).K(zVar);
        }
        dr drVar = new dr(8, this);
        this.v = drVar;
        zp1Var.p(drVar);
        ?? r11 = new bz0.b() { // from class: l03
            @Override // bz0.b
            public final void i(boolean z) {
                this.e.f.l(n03.d, z);
            }
        };
        this.n = r11;
        y13Var.u().a(vVar);
        y13Var.m(yVar);
        qs1Var.h(this.q.b(new on0(19, this)));
        bz0Var.a(r11);
        propertyModel.p(n03.a, new gs(10, this));
        propertyModel.p(n03.b, new ie0(22, this));
        String string = activity.getString(R$string.accessibility_bottom_tab_strip_expand_tab_sheet);
        String string2 = activity.getString(R$string.bottom_tab_grid_new_tab);
        propertyModel.p(n03.g, string);
        propertyModel.p(n03.h, string2);
        propertyModel.l(n03.c, true);
        Tab tabI = y13Var.i();
        if (tabI != null) {
            e(tabI.getId());
        }
    }

    public final List<Tab> d(int i) {
        return this.i.u().c().B(i);
    }

    public final void e(int i) {
        if (this.x) {
            i = -1;
        }
        List<Tab> listD = d(i);
        int size = listD.size();
        a aVar = this.h;
        if (size < 2) {
            ((u) aVar).k(null);
            this.w = false;
        } else {
            ((u) aVar).k(listD);
            this.w = true;
        }
        if (this.w) {
            new Handler().post(new m03(this, i, listD));
        }
        boolean z = this.w;
        bi biVar = (bi) ((c01) this.k).f;
        biVar.l = z;
        boolean zB = biVar.b();
        biVar.e.l(ci.d, zB);
        i = zB ? biVar.j : 0;
        jj jjVar = biVar.g;
        jjVar.k(i, jjVar.b());
        biVar.d();
    }

    @Override // defpackage.vb
    public final zp1<Boolean> getHandleBackPressChangedSupplier() {
        nw2<m.b> nw2Var = this.m;
        return (nw2Var == null || !nw2Var.f()) ? new aq1() : nw2Var.get().getHandleBackPressChangedSupplier();
    }

    @Override // defpackage.vb
    public final int o() {
        nw2<m.b> nw2Var = this.m;
        if (nw2Var == null || !nw2Var.f()) {
            return 1;
        }
        return nw2Var.get().o();
    }

    public void setupLeftButtonOnClickListener(View.OnClickListener onClickListener) {
        this.f.p(n03.a, onClickListener);
    }
}
