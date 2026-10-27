package org.chromium.components.browser_ui.bottomsheet;

import android.animation.ValueAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import defpackage.C0175b;
import defpackage.C0188h0;
import defpackage.aq1;
import defpackage.ef2;
import defpackage.ii;
import defpackage.jf2;
import defpackage.ki;
import defpackage.mb3;
import defpackage.no0;
import defpackage.nw2;
import defpackage.ok3;
import defpackage.t41;
import defpackage.uv;
import defpackage.vb;
import defpackage.ve3;
import defpackage.vi;
import defpackage.zj;
import defpackage.zp1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import org.chromium.base.Callback;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: BottomSheetControllerImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f implements j {
    public BottomSheet e;
    public ViewGroup f;
    public PriorityQueue<ii> g;
    public boolean h;
    public boolean i;
    public ok3 j;
    public int l;
    public ii m;
    public final nw2<ef2> n;
    public C0188h0 p;
    public ki s;
    public final aq1<Boolean> q = new aq1<>();
    public final ArrayList k = new ArrayList();
    public final mb3 o = new mb3(new e(this, 0));
    public final a r = new a();

    /* JADX INFO: compiled from: BottomSheetControllerImpl.java */
    public class a implements vb {
        public a() {
        }

        @Override // defpackage.vb
        public final zp1<Boolean> getHandleBackPressChangedSupplier() {
            return f.this.q;
        }

        @Override // defpackage.vb
        public final int o() {
            Boolean bool = Boolean.TRUE;
            f fVar = f.this;
            if (bool.equals(fVar.e.t.l().f)) {
                fVar.e.t.a();
                return 0;
            }
            fVar.e.s(fVar.e.g(), 2, true);
            return 0;
        }
    }

    public f(nw2<ef2> nw2Var, Callback<View> callback, Window window, t41 t41Var, nw2<ViewGroup> nw2Var2) {
        this.n = nw2Var;
        this.j = new ok3(this, callback, window, t41Var, nw2Var2, 1);
    }

    public static void n(f fVar) {
        BottomSheet bottomSheet = fVar.e;
        if (bottomSheet.y) {
            bottomSheet.s(bottomSheet.g(), 3, true);
        }
    }

    /* JADX WARN: Type inference failed for: r8v21, types: [T, org.chromium.components.browser_ui.bottomsheet.BottomSheet] */
    /* JADX WARN: Type inference failed for: r8v23, types: [T, org.chromium.components.browser_ui.bottomsheet.e] */
    public static void o(f fVar, Callback callback, Window window, t41 t41Var, nw2 nw2Var) {
        fVar.getClass();
        ViewGroup viewGroup = (ViewGroup) nw2Var.get();
        fVar.f = viewGroup;
        int i = 0;
        viewGroup.setVisibility(0);
        LayoutInflater.from(((ViewGroup) nw2Var.get()).getContext()).inflate(R$layout.bottom_sheet, fVar.f);
        BottomSheet bottomSheet = (BottomSheet) ((ViewGroup) nw2Var.get()).findViewById(R$id.bottom_sheet);
        fVar.e = bottomSheet;
        callback.onResult(bottomSheet);
        BottomSheet bottomSheet2 = fVar.e;
        bottomSheet2.j = (ViewGroup) bottomSheet2.getParent();
        bottomSheet2.w = (TouchRestrictingFrameLayout) bottomSheet2.findViewById(R$id.bottom_sheet_toolbar_container);
        TouchRestrictingFrameLayout touchRestrictingFrameLayout = (TouchRestrictingFrameLayout) bottomSheet2.findViewById(R$id.bottom_sheet_content);
        bottomSheet2.u = touchRestrictingFrameLayout;
        touchRestrictingFrameLayout.e = bottomSheet2;
        bottomSheet2.m = bottomSheet2.j.getWidth();
        bottomSheet2.n = bottomSheet2.j.getHeight();
        bottomSheet2.v();
        bottomSheet2.j.addOnLayoutChangeListener(new b(bottomSheet2, window, t41Var));
        bottomSheet2.w.addOnLayoutChangeListener(new c(bottomSheet2));
        bottomSheet2.j.removeView(bottomSheet2);
        fVar.e.B = fVar.p;
        fVar.g = new PriorityQueue<>(1, new no0(3));
        HashMap mapE = PropertyModel.e(jf2.l);
        PropertyModel.j jVar = jf2.a;
        int i2 = zj.a;
        PropertyModel.d dVar = new PropertyModel.d();
        dVar.a = 0;
        mapE.put(jVar, dVar);
        PropertyModel.h hVar = jf2.b;
        PropertyModel.a aVar = new PropertyModel.a();
        aVar.a = true;
        mapE.put(hVar, aVar);
        PropertyModel.l<View> lVar = jf2.c;
        ?? r8 = fVar.e;
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = r8;
        mapE.put(lVar, gVar);
        PropertyModel.h hVar2 = jf2.d;
        PropertyModel.a aVar2 = new PropertyModel.a();
        aVar2.a = false;
        mapE.put(hVar2, aVar2);
        PropertyModel.l<Runnable> lVar2 = jf2.f;
        ?? eVar = new e(fVar, 1);
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = eVar;
        PropertyModel propertyModelP = C0175b.p(mapE, lVar2, gVar2, mapE);
        BottomSheet bottomSheet3 = fVar.e;
        bottomSheet3.f.a(new g(fVar, propertyModelP));
        while (true) {
            ArrayList arrayList = fVar.k;
            if (i >= arrayList.size()) {
                arrayList.clear();
                fVar.j = null;
                return;
            } else {
                BottomSheet bottomSheet4 = fVar.e;
                bottomSheet4.f.a((vi) arrayList.get(i));
                i++;
            }
        }
    }

    @Override // defpackage.ji
    public final void a(ii iiVar, boolean z, int i) {
        BottomSheet bottomSheet = this.e;
        if (bottomSheet == null) {
            return;
        }
        ii iiVar2 = bottomSheet.t;
        if (iiVar != iiVar2) {
            this.g.remove(iiVar);
            return;
        }
        if (this.h) {
            return;
        }
        if (bottomSheet.q != 0) {
            this.h = true;
            bottomSheet.s(0, i, z);
        } else {
            if (iiVar2 != null) {
                iiVar2.destroy();
            }
            u(z);
        }
    }

    @Override // defpackage.ji
    public final int b() {
        if (this.e != null) {
            return r0.n;
        }
        return 0;
    }

    @Override // defpackage.ji
    public final void c(ii iiVar, boolean z) {
        a(iiVar, z, 0);
    }

    @Override // defpackage.ji
    public final boolean d(ii iiVar, boolean z) {
        if (iiVar == null) {
            throw new uv("Attempting to show null content in the sheet!");
        }
        if (this.e == null) {
            this.j.run();
        }
        if (iiVar == this.e.t || this.g.contains(iiVar)) {
            return iiVar == this.e.t;
        }
        boolean z2 = this.e.t != null && iiVar.e() < this.e.t.e() && (this.e.y ^ true);
        this.g.add(iiVar);
        ii iiVar2 = this.e.t;
        mb3 mb3Var = this.o;
        if (iiVar2 == null && !mb3Var.b()) {
            u(z);
            return true;
        }
        if (z2) {
            this.i = true;
            this.g.add(this.e.t);
            if (!mb3Var.b()) {
                this.e.s(0, 0, z);
                return true;
            }
            this.e.u(null);
        }
        return false;
    }

    @Override // defpackage.ji
    public final void e(vi viVar) {
        BottomSheet bottomSheet = this.e;
        if (bottomSheet != null) {
            bottomSheet.f.c(viVar);
        } else {
            this.k.remove(viVar);
        }
    }

    @Override // defpackage.ji
    public final ef2 f() {
        return this.n.get();
    }

    @Override // defpackage.ji
    public final void g(vi viVar) {
        BottomSheet bottomSheet = this.e;
        if (bottomSheet == null) {
            this.k.add(viVar);
        } else {
            bottomSheet.f.a(viVar);
        }
    }

    @Override // defpackage.ji
    public final ii h() {
        BottomSheet bottomSheet = this.e;
        if (bottomSheet == null) {
            return null;
        }
        return bottomSheet.t;
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [T, org.chromium.components.browser_ui.bottomsheet.BottomSheet] */
    /* JADX WARN: Type inference failed for: r2v5, types: [T, ve3] */
    @Override // defpackage.ji
    public final PropertyModel i() {
        HashMap mapE = PropertyModel.e(jf2.l);
        PropertyModel.j jVar = jf2.a;
        int i = zj.a;
        PropertyModel.d dVar = new PropertyModel.d();
        dVar.a = 0;
        mapE.put(jVar, dVar);
        PropertyModel.h hVar = jf2.b;
        PropertyModel.a aVar = new PropertyModel.a();
        aVar.a = true;
        mapE.put(hVar, aVar);
        PropertyModel.l<View> lVar = jf2.c;
        ?? r2 = this.e;
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = r2;
        mapE.put(lVar, gVar);
        PropertyModel.h hVar2 = jf2.d;
        PropertyModel.a aVar2 = new PropertyModel.a();
        aVar2.a = false;
        mapE.put(hVar2, aVar2);
        PropertyModel.l<Runnable> lVar2 = jf2.f;
        ?? ve3Var = new ve3(8, this);
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = ve3Var;
        return C0175b.p(mapE, lVar2, gVar2, mapE);
    }

    @Override // defpackage.ji
    public final int j() {
        BottomSheet bottomSheet = this.e;
        if (bottomSheet == null) {
            return 0;
        }
        return bottomSheet.q;
    }

    @Override // defpackage.ji
    public final boolean k() {
        if (this.e != null && !this.o.b()) {
            BottomSheet bottomSheet = this.e;
            if (!(bottomSheet.l != null && bottomSheet.r == 0) && bottomSheet.y && bottomSheet.n()) {
                this.e.s(1, 0, true);
                return true;
            }
        }
        return false;
    }

    @Override // defpackage.ji
    public final void l() {
        if (this.e == null || this.o.b()) {
            return;
        }
        BottomSheet bottomSheet = this.e;
        if ((bottomSheet.l != null && bottomSheet.r == 0) || bottomSheet.t == null) {
            return;
        }
        bottomSheet.s(2, 0, true);
    }

    @Override // defpackage.ji
    public final boolean m() {
        BottomSheet bottomSheet = this.e;
        return bottomSheet != null && bottomSheet.y;
    }

    public final void p() {
        if (this.e == null) {
            return;
        }
        Iterator<ii> it = this.g.iterator();
        while (it.hasNext()) {
            if (!it.next().k()) {
                it.remove();
            }
        }
        ii iiVar = this.e.t;
        if (iiVar == null || !iiVar.k()) {
            a(iiVar, true, 0);
        }
        this.m = null;
        this.l = -1;
    }

    public final void q() {
        BottomSheet bottomSheet = this.e;
        if (bottomSheet != null) {
            StringBuilder sb = new StringBuilder("Sheet destroyed: state: ");
            sb.append(bottomSheet.q);
            sb.append(", content null: ");
            sb.append(bottomSheet.t == null);
            Log.i("cr_BottomSheet", sb.toString());
            bottomSheet.z = true;
            bottomSheet.x = false;
            bottomSheet.f.clear();
            ValueAnimator valueAnimator = bottomSheet.l;
            if (valueAnimator != null) {
                valueAnimator.end();
            }
            bottomSheet.l = null;
        }
    }

    public final boolean r() {
        if (this.e == null || this.o.b()) {
            return false;
        }
        if (h() != null && h().o()) {
            return true;
        }
        BottomSheet bottomSheet = this.e;
        if (!bottomSheet.y) {
            return false;
        }
        this.e.s(bottomSheet.g(), 2, true);
        return true;
    }

    public final boolean s() {
        BottomSheet bottomSheet = this.e;
        return (bottomSheet == null || bottomSheet.l == null || bottomSheet.r != 0) ? false : true;
    }

    public final void t(float f) {
        BottomSheet bottomSheet = this.e;
        if (bottomSheet != null) {
            bottomSheet.A = f;
            if (bottomSheet.q != 0 && bottomSheet.p <= bottomSheet.j(1)) {
                bottomSheet.r(bottomSheet.p, 1);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [ki] */
    public final void u(boolean z) {
        if (this.e.q != 0) {
            throw new uv("Showing next content before sheet is hidden!");
        }
        this.f.setVisibility(0);
        if (this.g.isEmpty()) {
            this.e.u(null);
            return;
        }
        ii iiVarPoll = this.g.poll();
        ii iiVar = this.e.t;
        if (iiVar != null) {
            iiVar.l().d(this.s);
        }
        if (iiVarPoll != null) {
            this.s = new Callback() { // from class: ki
                @Override // org.chromium.base.Callback
                public final void onResult(Object obj) {
                    this.e.w();
                }
            };
            iiVarPoll.l().p(this.s);
        }
        this.e.u(iiVarPoll);
        BottomSheet bottomSheet = this.e;
        bottomSheet.s(bottomSheet.t == null ? 0 : bottomSheet.n() ? 1 : bottomSheet.m() ? 2 : 3, 0, z);
    }

    public final int v(int i) {
        BottomSheet bottomSheet;
        mb3 mb3Var = this.o;
        boolean zB = mb3Var.b();
        int iA = mb3Var.a();
        if (!zB && (bottomSheet = this.e) != null) {
            int i2 = bottomSheet == null ? -1 : bottomSheet.r;
            this.l = i2;
            if (i2 == -1) {
                this.l = j();
            }
            this.m = h();
            this.e.s(0, i, false);
        }
        return iA;
    }

    public final void w() {
        ii iiVar;
        this.q.r(Boolean.valueOf((this.e == null || this.o.b() || (iiVar = this.e.t) == null || (!Boolean.TRUE.equals(iiVar.l().f) && !this.e.y)) ? false : true));
    }
}
