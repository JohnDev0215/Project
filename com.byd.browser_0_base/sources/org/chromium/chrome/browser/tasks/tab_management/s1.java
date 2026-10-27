package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.view.ViewGroup;
import defpackage.aq1;
import defpackage.bq;
import defpackage.d53;
import defpackage.ds2;
import defpackage.eu1;
import defpackage.g72;
import defpackage.hy0;
import defpackage.kj;
import defpackage.ku2;
import defpackage.ll;
import defpackage.n3;
import defpackage.ny0;
import defpackage.oj2;
import defpackage.p13;
import defpackage.qs1;
import defpackage.tj1;
import defpackage.uj1;
import defpackage.vb;
import defpackage.wp2;
import defpackage.x10;
import defpackage.y13;
import defpackage.yp2;
import defpackage.z7;
import defpackage.zb;
import defpackage.zb2;
import defpackage.zp1;
import defpackage.zx1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.b;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.TabListRecyclerView;
import org.chromium.chrome.browser.tasks.tab_management.c0;
import org.chromium.chrome.browser.tasks.tab_management.i;
import org.chromium.chrome.browser.tasks.tab_management.j0;
import org.chromium.chrome.browser.tasks.tab_management.m;
import org.chromium.chrome.browser.tasks.tab_management.n1;
import org.chromium.chrome.browser.tasks.tab_management.o1;
import org.chromium.chrome.browser.tasks.tab_management.p1;
import org.chromium.chrome.browser.tasks.tab_management.q1;
import org.chromium.chrome.browser.tasks.tab_management.s1;
import org.chromium.chrome.browser.tasks.tab_management.z0;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: TabSwitcherMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class s1 implements n1.a, TabListRecyclerView.d, j0.h, i.d, p1.a, vb {
    public final qs1<m.b> A;
    public z0.d B;
    public n1.b C;
    public i D;
    public hy0 E;
    public Runnable F;
    public boolean G;
    public int H;
    public int I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public int f82J;
    public boolean K;
    public final boolean L;
    public final int M;
    public final Context N;
    public wp2 O;
    public final Handler e;
    public final zx1 f;
    public final eu1 g;
    public final d h;
    public final PropertyModel i;
    public final y13 j;
    public final u1 k;
    public final t1 l;
    public final kj n;
    public final v1 o;
    public final ViewGroup p;
    public final boolean q;
    public final boolean r;
    public final boolean s;
    public final tj1 t;
    public final d53 u;
    public final ll z;
    public final org.chromium.base.b<n1.d> m = new org.chromium.base.b<>();
    public final aq1<Boolean> v = new aq1<>();
    public final aq1<Boolean> w = new aq1<>();
    public final x10 x = new x10(5, this);
    public final a y = new a();

    /* JADX INFO: compiled from: TabSwitcherMediator.java */
    public interface b {
    }

    /* JADX INFO: compiled from: TabSwitcherMediator.java */
    public interface c {
    }

    /* JADX INFO: compiled from: TabSwitcherMediator.java */
    public interface d {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v4, types: [d53, java.lang.Object] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public s1(Context context, d dVar, PropertyModel propertyModel, y13 y13Var, kj kjVar, ViewGroup viewGroup, final b bVar, c cVar, tj1 tj1Var, int i, qs1 qs1Var, zb zbVar, o1.a aVar) {
        this.h = dVar;
        this.i = propertyModel;
        this.j = y13Var;
        this.n = kjVar;
        this.t = tj1Var;
        this.M = i;
        propertyModel.n(b0.j, i);
        this.N = context;
        this.q = zb2.d(context);
        this.r = zb2.e(context);
        this.s = DeviceFormFactor.a(context);
        Object[] objArr = 0;
        if (qs1Var != null) {
            ll llVar = new ll();
            this.z = llVar;
            final Object[] objArr2 = objArr == true ? 1 : 0;
            qs1Var.h(llVar.b(new Callback(this) { // from class: c53
                public final /* synthetic */ s1 f;

                {
                    this.f = this;
                }

                @Override // org.chromium.base.Callback
                public final void onResult(Object obj) {
                    int i2 = objArr2;
                    s1 s1Var = this.f;
                    switch (i2) {
                        case 0:
                            hy0 hy0Var = (hy0) obj;
                            s1Var.E = hy0Var;
                            hy0Var.b(s1Var.y);
                            break;
                        default:
                            s1Var.getClass();
                            ((m.b) obj).getHandleBackPressChangedSupplier().p(s1Var.x);
                            break;
                    }
                }
            }));
        }
        t1 t1Var = new t1(this);
        this.l = t1Var;
        y13Var.m(t1Var);
        u1 u1Var = new u1(this, bVar, cVar);
        this.k = u1Var;
        v1 v1Var = new v1(this);
        this.o = v1Var;
        kjVar.o(v1Var);
        if (y13Var.y().isEmpty()) {
            y13Var.m(new w1(this));
        } else {
            y13Var.u().a(u1Var);
        }
        propertyModel.p(b0.c, this);
        p13 p13VarC = y13Var.u().c();
        propertyModel.l(b0.b, p13VarC != null ? p13VarC.isIncognito() : false);
        final int i2 = 1;
        propertyModel.l(b0.e, true);
        if (i != 2) {
            u();
            propertyModel.n(b0.g, kjVar.g());
        }
        if (i == 0) {
            propertyModel.n(b0.i, (int) context.getResources().getDimension(R$dimen.tab_grid_bottom_padding));
            if (zbVar != null && zb.b()) {
                zbVar.a(this, 11);
                l();
            }
        }
        this.p = viewGroup;
        Objects.requireNonNull(dVar);
        this.f = new zx1(21, dVar);
        this.g = new eu1(25, this);
        this.e = new Handler();
        this.L = o1.v(i);
        ?? r1 = new tj1.a() { // from class: d53
            @Override // tj1.a
            public final void b(boolean z) {
                s1.b bVar2 = bVar;
                if (!z) {
                    ((o1) bVar2).y();
                    return;
                }
                c0 c0Var = ((o1) bVar2).i;
                c0Var.g(3, 5);
                c0Var.g(6, 4);
            }
        };
        this.u = r1;
        ((uj1) tj1Var).b.a(r1);
        l();
        this.A = aVar;
        if (aVar != null) {
            aVar.h(new Callback(this) { // from class: c53
                public final /* synthetic */ s1 f;

                {
                    this.f = this;
                }

                @Override // org.chromium.base.Callback
                public final void onResult(Object obj) {
                    int i22 = i2;
                    s1 s1Var = this.f;
                    switch (i22) {
                        case 0:
                            hy0 hy0Var = (hy0) obj;
                            s1Var.E = hy0Var;
                            hy0Var.b(s1Var.y);
                            break;
                        default:
                            s1Var.getClass();
                            ((m.b) obj).getHandleBackPressChangedSupplier().p(s1Var.x);
                            break;
                    }
                }
            });
        }
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.a
    public final aq1 E0() {
        return this.w;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.a
    public final int L() {
        int i = this.M;
        if (i != 0) {
            return i != 2 ? 3 : 1;
        }
        return 0;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.a
    public final void Z0(boolean z) {
        Handler handler = this.e;
        handler.removeCallbacks(this.f);
        handler.removeCallbacks(this.g);
        y13 y13Var = this.j;
        if (!y13Var.r() || !j()) {
            boolean zT = y13Var.t();
            boolean z2 = this.L;
            d dVar = this.h;
            if (zT) {
                ((o1) dVar).w(y13Var.u().c(), oj2.H(), z2);
                t();
            } else if (bq.F.a()) {
                ku2 ku2VarT = ku2.t();
                try {
                    g72.l(this.N);
                    ArrayList arrayList = g72.f;
                    ku2VarT.close();
                    ((o1) dVar).x(arrayList, oj2.H(), z2);
                } catch (Throwable th) {
                    try {
                        ku2VarT.close();
                    } catch (Throwable unused) {
                    }
                    throw th;
                }
            }
        }
        PropertyModel propertyModel = this.i;
        if (!z) {
            propertyModel.l(b0.e, false);
        }
        n3.X0("MobileToolbarShowStackView");
        propertyModel.l(b0.a, true);
        l();
        this.H = y13Var.A();
        this.I = y13Var.k();
        propertyModel.l(b0.e, true);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.a
    public final boolean a() {
        boolean zQ = q();
        if (zQ && (!this.q || this.r)) {
            zb.c(11);
        }
        return zQ;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.a
    public final void b1(q1.a aVar) {
        this.m.c(aVar);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.TabListRecyclerView.d
    public final void d() {
        Iterator<n1.d> it = this.m.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((n1.d) aVar.next()).d();
            }
        }
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.TabListRecyclerView.d
    public final void e() {
        Iterator<n1.d> it = this.m.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((n1.d) aVar.next()).e();
            }
        }
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.TabListRecyclerView.d
    public final void f() {
        Iterator<n1.d> it = this.m.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((n1.d) aVar.next()).k();
            }
        }
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.a
    public final void g(boolean z) {
        PropertyModel propertyModel = this.i;
        if (!z) {
            propertyModel.l(b0.e, false);
        }
        propertyModel.l(b0.a, false);
        l();
        propertyModel.l(b0.e, true);
        qs1<m.b> qs1Var = this.A;
        if (qs1Var == null || !qs1Var.f()) {
            return;
        }
        qs1Var.get().W(false);
    }

    @Override // defpackage.vb
    public final zp1<Boolean> getHandleBackPressChangedSupplier() {
        return this.v;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.TabListRecyclerView.d
    public final void h() {
        Iterator<n1.d> it = this.m.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((n1.d) aVar.next()).s();
            }
        }
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.a
    public final void i(ViewGroup viewGroup) {
        wp2 wp2Var = this.O;
        if (wp2Var == null) {
            return;
        }
        wp2Var.k = viewGroup;
        yp2 yp2Var = wp2Var.f;
        if (yp2Var != null) {
            yp2Var.b(viewGroup);
        }
    }

    public final boolean j() {
        hy0 hy0Var;
        if (!this.j.r() || (hy0Var = this.E) == null || !hy0Var.e()) {
            return false;
        }
        ((o1) this.h).w(null, false, this.L);
        return true;
    }

    public final List<Tab> k(int i) {
        return this.j.u().c().B(i);
    }

    public final void l() {
        this.w.r(Boolean.valueOf(w()));
        boolean z = true;
        if (!w() && this.F == null) {
            if (!this.i.k(b0.a) || this.M == 2 || this.j.i() == null) {
                z = false;
            }
        }
        this.v.r(Boolean.valueOf(z));
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.a
    public final ViewGroup n() {
        return this.p;
    }

    @Override // defpackage.vb
    public final int o() {
        return !q() ? 1 : 0;
    }

    public final boolean q() {
        z0.d dVar = this.B;
        if (dVar != null && dVar.m()) {
            return true;
        }
        Runnable runnable = this.F;
        if (runnable != null) {
            runnable.run();
            return true;
        }
        if (!this.i.k(b0.a)) {
            return false;
        }
        qs1<m.b> qs1Var = this.A;
        if (qs1Var != null && qs1Var.f() && qs1Var.get().m()) {
            return true;
        }
        if (this.M == 2) {
            return false;
        }
        y13 y13Var = this.j;
        if (y13Var.i() == null) {
            return false;
        }
        r(y13Var.k(), false);
        return true;
    }

    public final void r(int i, boolean z) {
        int i2;
        if (z && ((i2 = this.M) == 2 || i2 == 0)) {
            Tab tabF = z7.F(this.j.z(), i);
            ds2.b(tabF);
            if (i2 == 2) {
                ds2.c(tabF);
            }
        }
        this.K = true;
        n1.b bVar = this.C;
        if (bVar != null) {
            bVar.t(i, SystemClock.uptimeMillis());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /* JADX WARN: Type inference failed for: r3v1, types: [b53] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final defpackage.b53 s(org.chromium.chrome.browser.tab.Tab r3) {
        /*
            r2 = this;
            android.content.Context r0 = r2.N
            boolean r0 = defpackage.oj2.D(r0)
            if (r0 == 0) goto L24
            y13 r0 = r2.j
            boolean r0 = r0.r()
            boolean r1 = r3.isIncognito()
            if (r0 != r1) goto L24
            int r3 = r3.getId()
            java.util.List r3 = r2.k(r3)
            int r3 = r3.size()
            r0 = 1
            if (r3 == r0) goto L24
            goto L25
        L24:
            r0 = 0
        L25:
            if (r0 != 0) goto L29
            r2 = 0
            return r2
        L29:
            b53 r3 = new b53
            r3.<init>()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.tasks.tab_management.s1.s(org.chromium.chrome.browser.tab.Tab):b53");
    }

    public void setOnTabSelectingListener(n1.b bVar) {
        this.C = bVar;
    }

    public final void t() {
        int iIndex = this.j.u().c().index();
        if (this.L) {
            iIndex = 0;
        }
        this.i.p(b0.d, Integer.valueOf(iIndex));
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.a
    public final void t1() {
        qs1<m.b> qs1Var = this.A;
        if (qs1Var == null || !qs1Var.f()) {
            return;
        }
        qs1Var.get().W(false);
    }

    public final void u() {
        Context context = this.N;
        boolean zD = zb2.d(context);
        PropertyModel propertyModel = this.i;
        if (zD && !zb2.e(context)) {
            propertyModel.n(b0.f, 0);
            propertyModel.n(b0.h, 0);
        } else if (this.s) {
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.toolbar_height_no_shadow);
            propertyModel.n(b0.f, dimensionPixelSize);
            propertyModel.n(b0.h, dimensionPixelSize);
        } else {
            int iF = this.n.f();
            propertyModel.n(b0.f, iF);
            propertyModel.n(b0.h, iF);
        }
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.a
    public final void u0(n1.d dVar) {
        this.m.a(dVar);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.a
    public final void v0() {
        l();
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.a
    public final boolean w() {
        z0.d dVar = this.B;
        if (dVar != null && dVar.isVisible()) {
            return true;
        }
        qs1<m.b> qs1Var = this.A;
        return qs1Var != null && qs1Var.f() && qs1Var.get().isVisible();
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.a
    public final boolean w1() {
        return this.i.k(b0.a);
    }

    /* JADX INFO: compiled from: TabSwitcherMediator.java */
    public class a implements ny0.a {
        public a() {
        }

        @Override // ny0.a
        public final void a() {
            s1 s1Var = s1.this;
            ((o1) s1Var.h).w(s1Var.j.u().c(), false, s1Var.L);
            s1Var.t();
        }

        @Override // ny0.a
        public final void b() {
        }

        @Override // ny0.a
        public final void c() {
        }
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.a
    public final void z0(long j) {
    }
}
