package org.chromium.chrome.browser.keyboard_accessory;

import android.util.SparseArray;
import android.view.View;
import defpackage.aq1;
import defpackage.bh0;
import defpackage.gj3;
import defpackage.nh0;
import defpackage.rq0;
import defpackage.sq0;
import defpackage.v62;
import defpackage.vb;
import defpackage.wb1;
import defpackage.zj;
import defpackage.zp1;
import java.util.HashMap;
import java.util.HashSet;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: ManualFillingMediator.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends nh0 implements View.OnLayoutChangeListener, vb {
    public final PropertyModel e;
    public final aq1<Integer> f;
    public final gj3 g;
    public final aq1<Boolean> h;

    /* JADX INFO: compiled from: ManualFillingMediator.java */
    public class a extends nh0 {
        public a() {
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void F0(Tab tab) {
            c cVar = c.this;
            gj3 gj3Var = cVar.g;
            gj3Var.getClass();
            WebContents webContentsC = tab.c();
            if (webContentsC != null) {
                d dVarA = gj3Var.a(webContentsC);
                WebContents webContents = dVarA.a;
                if (webContents != null) {
                    webContents.z(dVarA.f);
                }
                dVarA.d = null;
                dVarA.b.clear();
                dVarA.e = false;
                ((HashMap) gj3Var.a).remove(webContentsC);
            }
            cVar.A1();
            cVar.B1();
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void T0(int i, Tab tab) {
            c.this.A1();
        }
    }

    /* JADX INFO: compiled from: ManualFillingMediator.java */
    public class b implements rq0.a {
        public b() {
        }

        @Override // rq0.a
        public final void a(sq0 sq0Var, Tab tab) {
            c.this.A1();
        }
    }

    /* JADX INFO: renamed from: org.chromium.chrome.browser.keyboard_accessory.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ManualFillingMediator.java */
    public class C0090c extends bh0 {
        public C0090c() {
        }

        @Override // defpackage.bh0, defpackage.vi
        public final void d(int i, int i2) {
            c.this.e.l(wb1.d, i != 0);
        }
    }

    public c() {
        new SparseArray();
        PropertyModel.n nVar = wb1.a;
        PropertyModel.p pVar = wb1.c;
        PropertyModel.n nVar2 = wb1.b;
        PropertyModel.n nVar3 = wb1.d;
        PropertyModel.n nVar4 = wb1.e;
        HashMap mapE = PropertyModel.e(new v62[]{nVar, pVar, nVar2, nVar3, nVar4});
        int i = zj.a;
        PropertyModel.a aVar = new PropertyModel.a();
        aVar.a = false;
        mapE.put(nVar, aVar);
        PropertyModel.d dVar = new PropertyModel.d();
        dVar.a = 4;
        mapE.put(pVar, dVar);
        PropertyModel.a aVar2 = new PropertyModel.a();
        aVar2.a = true;
        mapE.put(nVar2, aVar2);
        PropertyModel.a aVar3 = new PropertyModel.a();
        aVar3.a = false;
        mapE.put(nVar3, aVar3);
        PropertyModel.a aVar4 = new PropertyModel.a();
        aVar4.a = true;
        mapE.put(nVar4, aVar4);
        this.e = new PropertyModel(mapE);
        aq1<Integer> aq1Var = new aq1<>();
        this.f = aq1Var;
        this.g = new gj3(3);
        new HashSet();
        this.h = new aq1<>();
        new a();
        new b();
        new C0090c();
        aq1Var.r(0);
    }

    public final void A1() {
        if (z1()) {
            throw null;
        }
    }

    public final void B1() {
        if (z1()) {
            TraceEvent.q("ManualFillingMediator#refreshTabs", null);
            throw null;
        }
    }

    public final boolean a() {
        if (!(z1() && (y1(1) || y1(3) || y1(11)))) {
            return false;
        }
        A1();
        return true;
    }

    @Override // defpackage.vb
    public final zp1<Boolean> getHandleBackPressChangedSupplier() {
        return this.h;
    }

    @Override // defpackage.vb
    public final int o() {
        return !a() ? 1 : 0;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (z1()) {
            throw null;
        }
    }

    public final boolean y1(int i) {
        return this.e.h(wb1.c) == i;
    }

    public final boolean z1() {
        return false;
    }
}
